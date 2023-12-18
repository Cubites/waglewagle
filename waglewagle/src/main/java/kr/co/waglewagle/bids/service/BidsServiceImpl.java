package kr.co.waglewagle.bids.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean askPrice(BidsFormVO vo) throws BidsException {
		
		boolean result=true;
		int insert=0;
		int update=0;
		int check =0;
		
		//처음 호가인지 검사
		int initial = mapper.checkInitial(vo);
		//이전 호가보다 큰 가격인지, 가용 포인트에서 뺐을 때 0보다 크거나 같은지 검사 
		check = mapper.checkPoint(vo);
		
		
		
		if(check > 0 || initial == 0) {
			//값 넣는 쿼리
			insert = mapper.insertBids(vo);
			//업데이트 쿼리
			update = mapper.UpdateUsablePoint(vo);
			System.out.println(insert+" "+update);
		}
		
		
		
		if(insert <= 0 || update <= 0 || check <= 0 ) {
			result =false;
			throw new BidsException("호가가 완료되지 않았습니다.");
		}
			
		
		return result;
	}

}
