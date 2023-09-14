package com.lichfl.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecoConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String recoId;
	private String recoCode;
	private String recoDesc;

	@Column(name = "reco_param_value")
	private String recoParamValue;

	@Column(name = "status")
	private String recoStatus;

	private String recoModule;
	private String recoParam1;
	private String recoParam2;

	@CreationTimestamp
	private LocalDateTime creationDate;

	@UpdateTimestamp
	private LocalDateTime updateDate;

}
