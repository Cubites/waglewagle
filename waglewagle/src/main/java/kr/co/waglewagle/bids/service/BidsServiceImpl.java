package kr.co.waglewagle.bids.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.waglewagle.bids.mapper.BidsMapper;
import kr.co.waglewagle.bids.won.BidsException;
import kr.co.waglewagle.bids.won.BidsFormVO;

@Service
public class BidsServiceImpl implements BidsService {
	
	@Autowired
	private BidsMapper mapper;

	@Override
	public Integer getBidsCnt(int goodsId) {
		 Integer bidsCnt = mapper.countBidsByGoodsId(goodsId);
		return bidsCnt;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean askPrice(BidsFormVO vo,Integer preMaxBids) throws BidsException {

		boolean result = true;
		int insert = 0;
		int update = 0;

		
		// 값 넣는 쿼리
		insert = mapper.insertBids(vo);
		
		vo.setBids_price(vo.getBids_price() - preMaxBids);
		// 업데이트 쿼리
		update = mapper.UpdateUsablePoint(vo);
		System.out.println(insert + " " + update);

		if (insert == 0 || update == 0) {
			result = false;
			throw new BidsException("호가가 완료되지 않았습니다.");
		}

		return result;
	}

	@Override
	public Integer selectMaxBidsByUsersId(Integer users_id, Integer goods_id) {
		Map<String, Integer> map = new ConcurrentHashMap<>();
		map.put("users_id", users_id);
		map.put("goods_id", goods_id);
		Integer result = mapper.selectMaxBidsPriceByUsersId(map);

		return result == null ? 0 : result;
	}

	@Override
	public Integer bidsCheck(BidsFormVO vo) {
		System.out.println("여기 왔습니다.");
		Integer check = 0;
		Integer initial = mapper.checkInitial(vo);
		// 이전 호가보다 큰 가격인지, 가용 포인트에서 뺐을 때 0보다 크거나 같은지 검사
		// 검사 통과하면 최대호가 실패하면 null ( 첫호가일때는 최대가격이 없어서 null인데 같이 검사하면 첫호가인지 최대호가 아닌건지 구분이
		// 안감)
		check = mapper.checkPoint(vo);

		if(initial == 0) {
			return 0;
		}
		
	
		//첫호가 아닐때 검사
		//check = mapper.checkPoint(vo);
		if(check != null && vo.getBids_price() - check <= 0) {
			return null;
		}
		
		return check;
		
	}

}
