package com.lichfl.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class RecoCustomRepo {

	@PersistenceContext
	EntityManager entityManager;

	public List<BookDto> fetchBookResults(RecoFilter recoFilter) {

		String sqlQuery = "SELECT to_char(bro_key_no) as broKeyNo  , to_char(BRO_TRAN_CODE) as tranCode,\r\n" + "       to_char(BRO_DOC_NO) as docNo,\r\n"
				+ "       to_char(BRO_DOC_DT) as docDate,\r\n"
				+ "        to_char(TRIM(LEADING '0' FROM BRO_chq_no))as chequeNo,\r\n"
				+ "       ---to_char(BRO_chq_no) as chequeNo,\r\n" + "       to_char(BRO_FLEX_27) as pMode,\r\n"
				+ "       to_char(BRO_VALUE_DT) as valueDate,\r\n" + "       to_char(BRO_FLEX_11) as micrCode,\r\n"
				+ "      to_char(BRO_FLEX_13) as loanNo, \r\n" + "       to_char(BRO_FLEX_15) as receiptNo,\r\n"
				+ "       to_char(BRO_FC_AMT) as orgAmnt,\r\n"
				+ "       to_char(BRO_FC_AMT - BRO_fc_adj_AMT) as unadjAmnt,\r\n"
				+ "       to_char(BRO_DRCR_FLAG) as drCr\r\n" + "    from    \r\n"
				+ "  table(BANK_RECO_PIPE_LIC(:glCode,:fromDate,:toDate,:catg))X \r\n"
				+ "         WHERE BRO_DRCR_FLAG = :tranType\r\n" + "    AND BRO_FLEX_27 = :pmode";

		Query query = entityManager.createNativeQuery(sqlQuery);
		query.setParameter("glCode", recoFilter.getBankCode())
				.setParameter("fromDate", recoFilter.getDatetimepickerFrom())
				.setParameter("toDate", recoFilter.getDatetimepickerTo())
				.setParameter("catg", recoFilter.getMatchingType()).setParameter("tranType", recoFilter.getTranType())
				.setParameter("pmode", recoFilter.getPMode());

		List<Object[]> resulList = query.getResultList();

		List<BookDto> bookDtoList = new ArrayList<>();

		for (Object[] row : resulList) {
			BookDto dto1 = new BookDto();

			try {
				dto1.setBroKeyNo(String.valueOf(row[0]));
				dto1.setTranCode(String.valueOf(row[1]));
				dto1.setDocNo(String.valueOf(row[2]));
				dto1.setDocDate(String.valueOf(row[3]));
				dto1.setChequeNo(String.valueOf(row[4]));
				dto1.setPayMode(String.valueOf(row[5]));
				dto1.setValueDate(String.valueOf(row[6]));
				dto1.setMicrCode(String.valueOf(row[7]));
				dto1.setLoanNo(String.valueOf(row[8]));
				dto1.setReceiptNo(String.valueOf(row[9]));
				dto1.setOrgAmnt(String.valueOf(row[10]));
				dto1.setUnadjAmnt(String.valueOf(row[11]));
				dto1.setDrCr(String.valueOf(row[12]));
				bookDtoList.add(dto1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bookDtoList;
	}

}
