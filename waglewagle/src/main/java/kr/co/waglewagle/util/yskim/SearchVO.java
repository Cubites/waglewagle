package kr.co.waglewagle.util.yskim;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchVO {
	private Integer pageNum;
	private Integer amount;
	
	public SearchVO(int i, int j) {
		i=1;
		j=10;
	}

}
