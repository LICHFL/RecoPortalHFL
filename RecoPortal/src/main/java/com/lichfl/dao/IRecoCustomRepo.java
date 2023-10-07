package com.lichfl.dao;

import java.util.List;

import com.lichfl.model.BookDto;
import com.lichfl.model.RecoFilter;
import com.lichfl.model.ReportParam;
import com.lichfl.model.UnmatchDto;

public interface IRecoCustomRepo {

	public List<BookDto> fetchBookResults(RecoFilter recoFilter);

	public int submitReport(ReportParam reportParam) throws Exception;

	public List<BookDto> getUnmatchRecords(UnmatchDto unmatchDto);

}
