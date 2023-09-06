package com.lichfl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lichfl.model.BookDto;

@Repository
public class RecoCustomRepoTemplate {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate, String catg) throws Exception {

		String sqlQuery = "SELECT to_char(BRO_TRAN_CODE) as tranCode,\r\n" + "       to_char(BRO_DOC_NO) as docNo,\r\n"
				+ "       to_char(BRO_DOC_DT) as docDate,\r\n" + "       to_char(BRO_FLEX_11) as chequeNo,\r\n"
				+ "       to_char(BRO_FLEX_25) as pMode,\r\n" + "       to_char(BRO_VALUE_DT) as valueDate,\r\n"
				+ "       '123456789' as micrCode,\r\n" + "       to_char(BRO_FLEX_13) as loanNo,\r\n"
				+ "       to_char(BRO_FC_ORG_AMT) as orgAmnt,\r\n" + "       to_char(BRO_LC_ORG_AMT) as unadjAmnt,\r\n"
				+ "       to_char(BRO_DRCR_FLAG) as drCr\r\n" + "  FROM TABLE(BANK_RECO_PIPE_LIC(:glCode,\r\n"
				+ "                                :fromDate,\r\n" + "                                :toDate,\r\n"
				+ "                                :catg)) X\r\n" + " WHERE BRO_DRCR_FLAG = 'C'\r\n"
				+ "   AND BRO_FLEX_27 = 'CQ'";

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("glCode", glCode)
				.addValue("fromDate", fromDate).addValue("toDate", toDate).addValue("catg", catg);

		List<BookDto> bookList;
		try {
			bookList = jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(BookDto.class), namedParameters);
		} catch (DataAccessException e) {

			throw new Exception(e.getMessage());
		}

		return bookList;
	}

}
