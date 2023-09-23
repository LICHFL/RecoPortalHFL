package com.lichfl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;

import lombok.extern.slf4j.Slf4j;

@Repository
@PropertySource("classpath:sql.properties")
@Slf4j
public class RecoMatchRecordsRepo {

	@Value("${reco.matchListQuery}")
	String getMatchListQuery;

	@Value("${reco.payModeSql}")
	String payModeSql;

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public List<BookDto> fetchBookResults(RecoFilter recoFilter) {
		System.out.println("RecoMatchRecordsRepo.fetchBookResults()");

		log.info("recoFilter ::" + recoFilter);

		String sqlQuery = getMatchListQuery;
		MapSqlParameterSource paramMap = null;
		if (!(recoFilter.getPMode().isBlank())) {
			sqlQuery = getMatchListQuery + " " + payModeSql;

			paramMap = new MapSqlParameterSource().addValue("glCode", recoFilter.getBankCode())
					.addValue("fromDate", recoFilter.getDatetimepickerFrom())
					.addValue("toDate", recoFilter.getDatetimepickerTo()).addValue("catg", recoFilter.getMatchingType())
					.addValue("drCr", recoFilter.getTranType()).addValue("payMode", recoFilter.getPMode());
		} else {

			paramMap = new MapSqlParameterSource().addValue("glCode", recoFilter.getBankCode())
					.addValue("fromDate", recoFilter.getDatetimepickerFrom())
					.addValue("toDate", recoFilter.getDatetimepickerTo()).addValue("catg", recoFilter.getMatchingType())
					.addValue("drCr", recoFilter.getTranType());
		}

		log.info("sqlQuery ::" + sqlQuery);
		List<BookDto> bookDtoList = jdbcTemplate.query(sqlQuery, paramMap, new BookDtoRowMapper());
		return bookDtoList;
	}

}
