<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table id="freezeTable" class="table table-striped table-hover dataTable no-footer table-responsive mt-2" width="100%" style="margin-top: -30px;">
    <thead>
        <tr>
            <th class="th-sm">Match</th>
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
        <tr>
            <td>
                <input type="checkbox" name="" id="">
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </tbody>
    <tfoot>
        <tr>
            <th class="th-sm">Match</th>
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

<script>

$('#freezeTable').dataTable({
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
    ]
});

</script>