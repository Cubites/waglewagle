package kr.co.waglewagle.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportsVO {
	private Integer reports_id;
	private Integer reports_type;
	private Integer users_id;
	private Integer goods_id;
	private String reports_content;
}
