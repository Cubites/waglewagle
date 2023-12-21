package kr.co.waglewagle.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class GoodsVO {
	private Integer goods_id;
	private String goods_title;
	private String goods_comment;
	private Integer goods_start_price;
	private Integer users_id;
	private Timestamp goods_exp;
	private Timestamp goods_date;
	private String goods_th_img;
	private Integer category_id;
	private Integer goods_access;
	private String goods_address;
	private Integer goods_avg_price;
}
