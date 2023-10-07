package com.lichfl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.lichfl.model.BookDto;

public class BookDtoRowMapper implements RowMapper<BookDto> {

	@Override
	public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {

		BookDto dto = new BookDto();
		dto.setBroKeyNo(rs.getString("broKeyNo"));
		dto.setTranCode(rs.getString("tranCode"));
		dto.setDocNo(rs.getString("docNo"));
		dto.setDocDate(rs.getString("docDate"));
		dto.setChequeNo(rs.getString("chequeNo"));
		dto.setPayMode(rs.getString("pMode"));
		dto.setValueDate(rs.getString("valueDate"));
		dto.setMicrCode(rs.getString("micrCode"));
		dto.setLoanNo(rs.getString("loanNo"));
		dto.setReceiptNo(rs.getString("receiptNo"));
		dto.setOrgAmnt(rs.getString("orgAmnt"));
		dto.setUnadjAmnt(rs.getString("unadjAmnt"));
		dto.setDrCr(rs.getString("drCr"));

		return dto;
	}

}
