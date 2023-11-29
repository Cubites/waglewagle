package kr.co.waglewagle.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticesVO {
	private Integer notices_id;
	private String notices_title;
	private String notices_content;
	private Date notices_date;
	private Integer admins_id;
}
