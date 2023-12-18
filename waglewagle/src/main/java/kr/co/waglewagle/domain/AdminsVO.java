package kr.co.waglewagle.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminsVO {
	private Integer admins_id;
	private String admins_email;
	private String admins_pwd;
	private String admins_phone;
	private Integer admins_role;
}
