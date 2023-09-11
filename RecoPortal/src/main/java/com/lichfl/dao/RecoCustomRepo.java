package com.lichfl.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lichfl.model.BookDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class RecoCustomRepo {

	@PersistenceContext
	EntityManager entityManager;

	public List<BookDto> fetchBookResults(String glCode, String fromDate, String toDate, String catg, String tranType) {

		/*
		 * String sqlQuery = "SELECT BRO_TRAN_CODE as tranCode,\r\n" +
		 * "       BRO_DOC_NO as docNo,\r\n" + "       BRO_DOC_DT as docDate,\r\n" +
		 * "       BRO_FLEX_11 as chequeNo,\r\n" + "       BRO_FLEX_25 as pMode,\r\n" +
		 * "       BRO_VALUE_DT as valueDate,\r\n" +
		 * "       '123456789' as micrCode,\r\n" +
		 * "       BRO_FLEX_13 as loanNo, BRO_FLEX_13 as receiptNo,\r\n" +
		 * "       BRO_FC_ORG_AMT as orgAmnt,\r\n" +
		 * "       BRO_LC_ORG_AMT as unadjAmnt,\r\n" +
		 * "       BRO_DRCR_FLAG as drCr\r\n" +
		 * "  FROM TABLE(BANK_RECO_PIPE_LIC(:glCode,\r\n" +
		 * "                                :fromDate,\r\n" +
		 * "                                :toDate,\r\n" +
		 * "                                :catg)) X\r\n" +
		 * " WHERE BRO_DRCR_FLAG = :tranType \r\n" + "   AND BRO_FLEX_27 = 'CQ'";
		 */
		String sqlQuery = "   \r\n"
				+ "SELECT to_char(BRO_TRAN_CODE) as tranCode,\r\n"
				+ "       to_char(BRO_DOC_NO) as docNo,\r\n"
				+ "       to_char(BRO_DOC_DT) as docDate,\r\n"
				+ "         to_char(TRIM(LEADING '0' FROM BRO_chq_no))as chequeNo,\r\n"
				+ "       to_char(BRO_FLEX_27) as pMode,\r\n"
				+ "       to_char(BRO_VALUE_DT) as valueDate,\r\n"
				+ "       to_char(BRO_FLEX_11) as micrCode,\r\n"
				+ "      to_char(BRO_FLEX_13) as loanNo, \r\n"
				+ "       to_char(BRO_FLEX_15) as receiptNo,\r\n"
				+ "       to_char(BRO_FC_AMT) as orgAmnt,\r\n"
				+ "       to_char(BRO_FC_AMT - BRO_fc_adj_AMT) as unadjAmnt,\r\n"
				+ "       to_char(BRO_DRCR_FLAG) as drCr\r\n"
				+ "    from    \r\n"
				+ "  TABLE(BANK_RECO_PIPE_LIC(:glCode,:fromDate,:toDate,:catg))X \r\n"
				+ "         WHERE BRO_DRCR_FLAG = :tranType\r\n"
				+ "         AND BRO_FLEX_27 = 'CQ'";

		Query query = entityManager.createNativeQuery(sqlQuery);
		query.setParameter("glCode", glCode).setParameter("fromDate", fromDate).setParameter("toDate", toDate)
				.setParameter("catg", catg).setParameter("tranType", tranType);

		List<Object[]> resulList = query.getResultList();

		List<BookDto> bookDtoList = new ArrayList<>();

		for (Object[] row : resulList) {
			BookDto dto = new BookDto();

			try {
				dto.setTranCode((String) row[0]);
				dto.setDocNo(String.valueOf(row[1]));
				dto.setDocDate(String.valueOf(row[2]));
				dto.setChequeNo(String.valueOf(row[3]));
				dto.setPayMode(String.valueOf(row[4]));
				dto.setValueDate(String.valueOf(row[5]));
				dto.setMicrCode(String.valueOf(row[6]));
				dto.setLoanNo(String.valueOf(row[7]));
				dto.setReceiptNo(String.valueOf(row[8]));
				dto.setOrgAmnt(String.valueOf(row[9]));
				dto.setUnadjAmnt(String.valueOf(row[10]));
				dto.setDrCr(String.valueOf(row[11]));
				bookDtoList.add(dto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return bookDtoList;
	}

}
