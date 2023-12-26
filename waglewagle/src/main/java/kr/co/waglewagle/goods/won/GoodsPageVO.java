package kr.co.waglewagle.goods.won;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsPageVO {

	private Integer page;
	private String searchWord;
	private Integer category_id;
	private Integer startNum;
	private static Integer pageStep = 20;
	private	Long totalPage;
	private Long totalrowsCnt;
	
	private List<Map<String,Object>> goodsList;

	public GoodsPageVO(Integer page, Integer category, String searchWord) {
		this.page = page;
		this.searchWord = searchWord;
		this.category_id = category;

		setStartNum();
	}

	private void setStartNum() {
		// 0,10,20,30으로감
		this.startNum = (this.page - 1) * this.pageStep;
	}

	public void setTotalrowsCnt(Long cnt) {

		this.totalrowsCnt = cnt;
		setTotalPage(cnt);

	}

	private void setTotalPage(Long cnt) {
		// 전체 테이블 수를 페이지 step으로 나눈 것
		this.totalPage = cnt / this.pageStep;

		// 나머지 있으면 1페이지 추가
		if (cnt % this.pageStep > 0) {
			this.totalPage++;
		}
	}


}
