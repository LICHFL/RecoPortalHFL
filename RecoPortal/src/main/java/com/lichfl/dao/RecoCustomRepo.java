package com.lichfl.dao;

import java.util.ArrayList;
import java.util.Collections;
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

		/*
		 * String sqlQuery =
		 * "SELECT to_char(bro_key_no) as broKeyNo  , to_char(BRO_TRAN_CODE) as tranCode,\r\n"
		 * + "       to_char(BRO_DOC_NO) as docNo,\r\n" +
		 * "       to_char(BRO_DOC_DT) as docDate,\r\n" +
		 * "        to_char(TRIM(LEADING '0' FROM BRO_chq_no))as chequeNo,\r\n" +
		 * "       ---to_char(BRO_chq_no) as chequeNo,\r\n" +
		 * "       to_char(BRO_FLEX_27) as pMode,\r\n" +
		 * "       to_char(BRO_VALUE_DT) as valueDate,\r\n" +
		 * "       to_char(BRO_FLEX_11) as micrCode,\r\n" +
		 * "      to_char(BRO_FLEX_13) as loanNo, \r\n" +
		 * "       to_char(BRO_FLEX_15) as receiptNo,\r\n" +
		 * "       to_char(BRO_FC_AMT) as orgAmnt,\r\n" +
		 * "       to_char(BRO_FC_AMT - BRO_fc_adj_AMT) as unadjAmnt,\r\n" +
		 * "       to_char(BRO_DRCR_FLAG) as drCr\r\n" + "    from    \r\n" +
		 * "  table(BANK_RECO_PIPE_LIC(:glCode,:fromDate,:toDate,:catg))X \r\n" +
		 * "         WHERE BRO_DRCR_FLAG = :tranType\r\n" +
		 * "     AND BRO_FLEX_27 in (:pmode)";
		 */

		String inSql = String.join(",", Collections.nCopies(recoFilter.getPMode().size(), "?"));

		
		
		
		String query1 = "SELECT to_char(bro_key_no) as broKeyNo  , to_char(BRO_TRAN_CODE) as tranCode,  to_char(BRO_DOC_NO) as docNo, to_char(BRO_DOC_DT) as docDate, to_char(TRIM(LEADING '0' FROM BRO_chq_no))as chequeNo,   to_char(BRO_VALUE_DT) as valueDate,      to_char(BRO_FLEX_11) as micrCode,   to_char(BRO_FLEX_13) as loanNo, to_char(BRO_FLEX_15) as receiptNo, to_char(BRO_FC_AMT) as orgAmnt, to_char(BRO_FC_AMT - BRO_fc_adj_AMT) as unadjAmnt,  to_char(BRO_DRCR_FLAG) as drCr   from  table(BANK_RECO_PIPE_LIC(?,?,?,?))X  WHERE BRO_DRCR_FLAG = ?   AND BRO_FLEX_27 in (" + inSql +")" ;

		/*
		 * String query1 =
		 * "SELECT to_char(bro_key_no) as broKeyNo  , to_char(BRO_TRAN_CODE) as tranCode,\\r\\n\"\r\n"
		 * +
		 * "				+ \"       to_char(BRO_DOC_NO) as docNo,\\r\\n\" + \"       to_char(BRO_DOC_DT) as docDate,\\r\\n\"\r\n"
		 * +
		 * "				+ \"        to_char(TRIM(LEADING '0' FROM BRO_chq_no))as chequeNo,\\r\\n\"\r\n"
		 * +
		 * "				+ \"       ---to_char(BRO_chq_no) as chequeNo,\\r\\n\" + \"       to_char(BRO_FLEX_27) as pMode,\\r\\n\"\r\n"
		 * +
		 * "				+ \"       to_char(BRO_VALUE_DT) as valueDate,\\r\\n\" + \"       to_char(BRO_FLEX_11) as micrCode,\\r\\n\"\r\n"
		 * +
		 * "				+ \"      to_char(BRO_FLEX_13) as loanNo, \\r\\n\" + \"       to_char(BRO_FLEX_15) as receiptNo,\\r\\n\"\r\n"
		 * + "				+ \"       to_char(BRO_FC_AMT) as orgAmnt,\\r\\n\"\r\n" +
		 * "				+ \"       to_char(BRO_FC_AMT - BRO_fc_adj_AMT) as unadjAmnt,\\r\\n\"\r\n"
		 * +
		 * "				+ \"       to_char(BRO_DRCR_FLAG) as drCr\\r\\n\" + \"    from    \\r\\n\"\r\n"
		 * + "				+ \"  table(BANK_RECO_PIPE_LIC(?,?,?,?))X \\r\\n\"\r\n" +
		 * "				+ \"         WHERE BRO_DRCR_FLAG = ? \\r\\n\" + \"     AND BRO_FLEX_27 in ("
		 * + inSql + ")";
		 */

		System.out.println("query ::" + query1);
		System.out.println("inSql ::" + inSql);

		/*
		 * String sqlQuery =
		 * "SELECT to_char(bro_key_no) as broKeyNo  , to_char(BRO_TRAN_CODE) as tranCode,\r\n"
		 * + "       to_char(BRO_DOC_NO) as docNo,\r\n" +
		 * "       to_char(BRO_DOC_DT) as docDate,\r\n" +
		 * "        to_char(TRIM(LEADING '0' FROM BRO_chq_no))as chequeNo,\r\n" +
		 * "       ---to_char(BRO_chq_no) as chequeNo,\r\n" +
		 * "       to_char(BRO_FLEX_27) as pMode,\r\n" +
		 * "       to_char(BRO_VALUE_DT) as valueDate,\r\n" +
		 * "       to_char(BRO_FLEX_11) as micrCode,\r\n" +
		 * "      to_char(BRO_FLEX_13) as loanNo, \r\n" +
		 * "       to_char(BRO_FLEX_15) as receiptNo,\r\n" +
		 * "       to_char(BRO_FC_AMT) as orgAmnt,\r\n" +
		 * "       to_char(BRO_FC_AMT - BRO_fc_adj_AMT) as unadjAmnt,\r\n" +
		 * "       to_char(BRO_DRCR_FLAG) as drCr\r\n" + "    from    \r\n" +
		 * "  table(BANK_RECO_PIPE_LIC(:glCode,:fromDate,:toDate,:catg))X \r\n" +
		 * "         WHERE BRO_DRCR_FLAG = :tranType\r\n" +
		 * "     AND BRO_FLEX_27 in (:pmode)";
		 */

		// AND BRO_FLEX_27 in ( :pmode)";

		Query query = entityManager.createNativeQuery(query1);
		query.setParameter(1, recoFilter.getBankCode());
		query.setParameter(2, recoFilter.getDatetimepickerFrom());
		query.setParameter(3, recoFilter.getDatetimepickerTo());
		query.setParameter(4, recoFilter.getMatchingType());
		query.setParameter(5, recoFilter.getTranType());
		for (int i = 6; i < recoFilter.getPMode().size() + 6; i++) {
			System.out.println(i - 6);
			query.setParameter(i, recoFilter.getPMode().get(i - 6));

		}

		System.out.println("query ::"+query);
		@SuppressWarnings("unchecked")
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
