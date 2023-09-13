package com.lichfl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "VW_BRS_USER_DETAILS")
public class BrsUserDetails {

	@Id
	@Column(name="USRLOGIN")
	private String usrLogin;
	
	@Column(name="USRBRANCHCODE")
	private String usrBranchCode;
	
	@Column(name="BANK_CODE")
	private String bankcode;
	
	@Column(name="USREMAIL")
	private String usrEmail;
	
	@Column(name="USRDESG")
	private String usrDesg;
	
	@Column(name = "USERNAME")
	private String userName;

}
