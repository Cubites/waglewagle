package kr.co.waglewagle.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVO {
	private Integer goods_id;
	private String goods_title;
	private String goods_comment;
	private Integer goods_start_price;
	private Integer users_id;
	private Date goods_exp;
	private Date goods_date;
	private String goods_th_img;
	private Integer category_id;
	private Integer goods_access;
	private String goods_address;
	private Integer goods_avg_price;
}
