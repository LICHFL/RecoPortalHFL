<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="reportTable" class="table table-striped table-hover dataTable no-footer table-responsive mt-2" width="100%">
    <thead>
        <tr>
            <th class="th-sm" style="width: 10%;">Report Id</th>            
            <th class="th-sm" style="width: 10%;">Bank Code</th>
            <th class="th-sm" style="width: 10%;">Report From Date</th>
            <th class="th-sm" style="width: 10%;">Report To Date</th>
            <th class="th-sm" style="width: 50%;">File Name</th>
            <th class="th-sm" style="width: 10%;">Report Run Date</th>
            <th class="th-sm" style="width: 10%;">Status</th>
            <th class="th-sm" style="width: 10%;">Download</th>           
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${reportList}" var="rept">
            <tr>
                <td>${rept.hrfRepId}</td>                
                <td>${rept.hrfBankCode}</td>
                <td>${rept.hrfSDt}</td>
                <td>${rept.hrfEDt}</td>
                <td>${rept.hrfReportFileName}</td>
                <td>${rept.hrfReportRunStart}</td>
                <td>${rept.hrfReportRunMsg}</td>
                <td><a href="${rept.hrfReportServerPath}" download>Download</a></td>
            </tr>
        </c:forEach>
    </tbody>    
</table>

