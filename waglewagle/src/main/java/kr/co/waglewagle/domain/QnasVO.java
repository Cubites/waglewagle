package kr.co.waglewagle.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnasVO {
	private Integer qnas_id;
	private String qnas_title;
	private String qnas_content;
	private Date qnas_date;
	private Integer users_id;
	private String qnas_reply;
	private Date qnas_reply_date;
	private Integer qnas_read;
	private Integer admins_id;
	private Integer rownum;

}
