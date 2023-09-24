package com.lichfl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "VW_BRS_USER_DETAILS")
public class BrsUserDetails {

	@Id
	@Column(name = "USRLOGIN", nullable = false)
	private String usrLogin;

	@Column(name = "USRBRANCHCODE")
	private String usrBranchCode;

	@Column(name = "BANK_CODE")
	private String bankcode;

	@Column(name = "USREMAIL")
	private String usrEmail;

	@Column(name = "USRDESG")
	private String usrDesg;

	@Column(name = "USERNAME", nullable = false)
	private String userName;

}
