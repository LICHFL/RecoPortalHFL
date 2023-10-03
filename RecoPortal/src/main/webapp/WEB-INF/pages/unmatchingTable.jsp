<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="unmatchingTable" class="table table-striped table-hover dataTable no-footer table-responsive mt-2" width="100%" style="margin-top: -30px;">
    <thead>
        <tr>
            <th class="th-sm">Match</th>
            <th class="th-sm">Match ID</th>
            <th class="th-sm">Tran Code</th>
            <th class="th-sm">Doc No</th>
            <th class="th-sm">Doc Date</th>
            <th class="th-sm">Cheque No</th>
            <th class="th-sm">P mode</th>
            <th class="th-sm">Value Date</th>
            <th class="th-sm">Micr Code</th>
            <th class="th-sm">Loan Ac</th>
            <th class="th-sm">PLF Receipt No</th>
            <th class="th-sm">Org Amt</th>
            <th class="th-sm">Unadj Amt</th>
            <th class="th-sm">Dr/Cr</th>
            <th class="th-sm">View Matching</th>
        </tr>
    </thead>
    <tbody>       
    <c:forEach items="${bookDtoList}" var="book">
            <tr>
                <td></td>
                <td>${book.broKeyNo}</td>
                <td>${book.tranCode}</td>
                <td>${book.docNo}</td>
                <td>${book.docDate}</td>
                <td>${book.chequeNo}</td>
                <td>${book.payMode}</td>
                <td>${book.valueDate}</td>
                <td>${book.micrCode}</td>
                <td>${book.loanNo}</td>
                <td>${book.receiptNo}</td>
                <td>${book.orgAmnt}</td>
                <td>${book.unadjAmnt}</td>
                <td>${book.drCr}</td>
                <td><button id="viewMatchedRecords" class="btn btn-sm btn-primary py-1 m-0" data-brokey="${book.broKeyNo}"><i class="fa fa-eye"></i> View</button></td>
            </tr>
      </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <th class="th-sm">Match</th>
            <th class="th-sm">Search Match ID </th>
            <th class="th-sm">Search Tran Code</th>
            <th class="th-sm">Search Doc No</th>
            <th class="th-sm">Search Doc Date</th>
            <th class="th-sm">Search Cheque No</th>
            <th class="th-sm">Search P mode</th>
            <th class="th-sm">Search Value Date</th>
            <th class="th-sm">Search Micr Code</th>
            <th class="th-sm">Search Loan Ac</th>
            <th class="th-sm">Search PLF Receipt No</th>
            <th class="th-sm">Search Org Amt</th>
            <th class="th-sm">Search Unadj Amt</th>
            <th class="th-sm">Search Dr/Cr</th>
            <th class="th-sm"></th>
        </tr>
    </tfoot>
</table>
<div class="modal fade" id="viewMatchedModal" tabindex="-1" role="dialog" aria-labelledby="viewMatchedModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <!--Content-->
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <h6 class="modal-title w-100" id="viewMatchedModalLabel"><i class="fa fa-history fa-fw"></i> View Matched Records</h6>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <!--Body-->
            <div class="modal-body lighten-5">
            	<table id="unmatchingTableView" class="table table-striped table-hover dataTable no-footer table-responsive mt-2" width="100%" style="margin-top: -30px;">
				    <thead>
				        <tr>
				            <th class="th-sm">Match</th>
				            <th class="th-sm">Match ID</th>
				            <th class="th-sm">Tran Code</th>
				            <th class="th-sm">Doc No</th>
				            <th class="th-sm">Doc Date</th>
				            <th class="th-sm">Cheque No</th>
				            <th class="th-sm">P mode</th>
				            <th class="th-sm">Value Date</th>
				            <th class="th-sm">Micr Code</th>
				            <th class="th-sm">Loan Ac</th>
				            <th class="th-sm">PLF Receipt No</th>
				            <th class="th-sm">Org Amt</th>
				            <th class="th-sm">Unadj Amt</th>
				            <th class="th-sm">Dr/Cr</th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach items="${bookDtoList}" var="book">
				            <tr>
				                <td>
				                    
				                </td>
				                <td>${book.broKeyNo}</td>
				                <td>${book.tranCode}</td>
				                <td>${book.docNo}</td>
				                <td>${book.docDate}</td>
				                <td>${book.chequeNo}</td>
				                <td>${book.payMode}</td>
				                <td>${book.valueDate}</td>
				                <td>${book.micrCode}</td>
				                <td>${book.loanNo}</td>
				                <td>${book.receiptNo}</td>
				                <td>${book.orgAmnt}</td>
				                <td>${book.unadjAmnt}</td>
				                <td>${book.drCr}</td>
				            </tr>
				        </c:forEach>
				    </tbody>
				    <tfoot>
				        <tr>
				            <th class="th-sm">Match</th>
				            <th class="th-sm">Search Match ID </th>
				            <th class="th-sm">Search Tran Code</th>
				            <th class="th-sm">Search Doc No</th>
				            <th class="th-sm">Search Doc Date</th>
				            <th class="th-sm">Search Cheque No</th>
				            <th class="th-sm">Search P mode</th>
				            <th class="th-sm">Search Value Date</th>
				            <th class="th-sm">Search Micr Code</th>
				            <th class="th-sm">Search Loan Ac</th>
				            <th class="th-sm">Search PLF Receipt No</th>
				            <th class="th-sm">Search Org Amt</th>
				            <th class="th-sm">Search Unadj Amt</th>
				            <th class="th-sm">Search Dr/Cr</th>
				        </tr>
				    </tfoot>
				</table>
            </div>
        </div>
    </div>
</div>
                        
<script>

unmatchTable = $('#unmatchingTable').DataTable({
    initComplete: function() {
        this.api()
            .columns()
            .every(function() {
                let column = this;
                let title = column.footer().textContent;

                // Create input element
                let input = document.createElement('input');
                input.placeholder = title;
                column.footer().replaceChildren(input);

                // Event listener for user input
                input.addEventListener('keyup', () => {
                    if (column.search() !== this.value) {
                        column.search(input.value).draw();
                    }
                });
            });
    },   
    dom: 'lfrtipB',
    buttons: [
        'excelHtml5'
    ],
    columnDefs: [
        {
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }
    ],
    select: {
        style: 'single',
        selector: 'td:first-child'
    },
});

unmatchTableView = $('#unmatchingTableView').DataTable({
    initComplete: function() {
        this.api()
            .columns()
            .every(function() {
                let column = this;
                let title = column.footer().textContent;

                // Create input element
                let input = document.createElement('input');
                input.placeholder = title;
                column.footer().replaceChildren(input);

                // Event listener for user input
                input.addEventListener('keyup', () => {
                    if (column.search() !== this.value) {
                        column.search(input.value).draw();
                    }
                });
            });
    },   
    dom: 'lfrtip',
    columnDefs: [
        {
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }
    ],
    select: {
        style: 'single',
        selector: 'td:first-child'
    },
});

$('#viewMatchedRecords').click(function(){
	$('#viewMatchedModal').modal('show');
});
</script>