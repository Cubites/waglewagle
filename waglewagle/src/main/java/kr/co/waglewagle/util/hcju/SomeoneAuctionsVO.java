package kr.co.waglewagle.util.hcju;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SomeoneAuctionsVO {
	private Integer goods_id;
	private String goods_title;
	private String goods_comment;
	private Integer goods_start_price;
	private Integer users_id;
	private Date goods_exp;
	private Date post_date;
	private String goods_th_img;
	private Integer bids_price;
}
