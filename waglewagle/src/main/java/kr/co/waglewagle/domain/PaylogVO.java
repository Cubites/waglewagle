package kr.co.waglewagle.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaylogVO {
	private Integer paylog_id;
	private Integer users_id;
	private Integer paylog_cash;
	private Integer paylog_type;
	private Date paylog_date;
}
