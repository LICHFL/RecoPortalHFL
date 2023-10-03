package com.lichfl.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.ReportParam;
import com.lichfl.model.UnmatchDto;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Repository
@PropertySource("classpath:sql.properties")
@Slf4j
public class RecoCustomRepoImpl implements IRecoCustomRepo {

	@Value("${reco.matchListQuery}")
	String getMatchListQuery;

	@Value("${reco.unmatchListQuery}")
	String unmatchListQuery;

	@Value("${reco.payModeSql}")
	String payModeSql;

	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

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
		List<BookDto> bookDtoList = namedJdbcTemplate.query(sqlQuery, paramMap, new BookDtoRowMapper());
		return bookDtoList;
	}

	@Transactional
	public int submitReport(ReportParam reportParam) throws Exception {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("hflbrs_fe_report");

		BigDecimal reportId = new BigDecimal("0");

		try {
			SqlParameterSource in = new MapSqlParameterSource().addValue("P_BANK_CODE", reportParam.getBankCodeRept())
					.addValue("S_DT", reportParam.getDatetimepickerFromRept())
					.addValue("E_DT", reportParam.getDatetimepickerToRept())
					.addValue("report_type", reportParam.getReportType()).addValue("x_report_id", reportId);

			Map<String, Object> out = jdbcCall.execute(in);
			reportId = (BigDecimal) out.get("X_REPORT_ID");
		} catch (Exception e) {

			e.printStackTrace();

			throw new Exception(e.getMessage());
		}

		return reportId.intValue();

	}

	@Override
	public List<BookDto> getUnmatchRecords(UnmatchDto unmatchDto) {

		String sqlQuery = unmatchListQuery;
		MapSqlParameterSource paramMap = null;
		if (!(unmatchDto.getPModeUnmatch().isBlank())) {
			sqlQuery = unmatchListQuery + " " + payModeSql;

			paramMap = new MapSqlParameterSource().addValue("glCode", unmatchDto.getBankCodeUnmatch())
					.addValue("fromDate", unmatchDto.getDatetimepickerFromUnmatch())
					.addValue("toDate", unmatchDto.getDatetimepickerToUnmatch())
					.addValue("catg", unmatchDto.getUnmatchingType()).addValue("drCr", unmatchDto.getTranUnmatchType())
					.addValue("payMode", unmatchDto.getPModeUnmatch());
			// .addValue("chqNo", recoFilter.getChqNo());
		} else {

			paramMap = new MapSqlParameterSource().addValue("glCode", unmatchDto.getBankCodeUnmatch())
					.addValue("fromDate", unmatchDto.getDatetimepickerFromUnmatch())
					.addValue("toDate", unmatchDto.getDatetimepickerToUnmatch())
					.addValue("catg", unmatchDto.getUnmatchingType()).addValue("drCr", unmatchDto.getTranUnmatchType());
		}

		log.info("sqlQuery ::" + sqlQuery);
		List<BookDto> bookDtoList = namedJdbcTemplate.query(sqlQuery, paramMap, new BookDtoRowMapper());
		return bookDtoList;
	}

}
