<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="resources/vendor/bootstrap/css/bootstrap.min.css">
<!-- DataTables CSS -->
<link
	href="resources/vendor/datatables/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link
	href="resources/vendor/datatables-responsive/dataTables.responsive.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Bootstrap Validator CSS -->
<link rel="stylesheet"
	href="resources/vendor/bootstrapValidator/css/bootstrapValidator.min.css">
<!-- Date time picker -->
<link rel="stylesheet"
	href="resources/vendor/datetimepicker/css/bootstrap-datetimepicker.min.css">
<!-- Select 2 -->
<!--<link rel="stylesheet"
	href="resources/vendor/select2/css/select2.min.css">-->
<link
	href="https://cdn.datatables.net/select/1.7.0/css/select.dataTables.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="resources/vendor/select2/css/select2-bootstrap.min.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="icon" href="resources/images/lichfl-favicon.png">
<title>LICHFL - RECO PORTAL</title>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="resources/vendor/bootstrap/js/jquery-3.3.1.min.js"></script>
<script src="resources/vendor/bootstrap/js/popper.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables JavaScript -->
<script src="resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script
	src="resources/vendor/datatables/js/dataTables.bootstrap4.min.js"></script>
<script
	src="resources/vendor/datatables-responsive/dataTables.responsive.js"></script>
<!-- MDB JS -->
<script src="resources/js/mdb.min.js"></script>
<!-- Bootstrap Validator JavaScript -->
<script
	src="resources/vendor/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<!-- Timeout JavaScript -->
<script src="resources/vendor/timeout/bootstrap-session-timeout.min.js"></script>
<!-- Select 2 -->
<script src="resources/vendor/moment/moment.min.js"></script>
<!-- Date time picker -->
<script
	src="resources/vendor/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- Select 2 -->
<!--  <script src="resources/vendor/select2/js/select2.full.min.js"></script>-->
<script
	src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script
	src="https://cdn.datatables.net/fixedcolumns/4.3.0/js/dataTables.fixedColumns.min.js"></script>
<script
	src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
<script src="resources/vendor/bootbox/bootbox.min.js"></script>

</head>

<body>
	<!-- Page wrapper -->
	<div class="page-wrapper" id="page-wrapper">
		<!-- Navbar -->
		<nav class="navbar navbar-expand-md navbar-light mb-2 no-content">
			<div class="web-page-title">
				<i class="fa fa-random"></i> RECO PORTAL
			</div>
			<ul class="navbar-nav ml-auto nav-flex-icons">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fa fa-user"></i> ${userData.userName}
				</a>
					<div class="dropdown-menu dropdown-menu-right dropdown-default"
						aria-labelledby="navbarDropdownMenuLink-333">
						<a class="dropdown-item" href="javascript:void(0)"><i
							class="fa fa-map-marker"></i> ${userData.usrBranchCode} </a> <a
							class="dropdown-item" href="javascript:void(0)"
							onclick="logout()"><i class="fa fa-sign-out"></i> Logout</a>
					</div></li>
			</ul>
		</nav>
		<!-- Navbar -->
		<!-- Page content -->
		<div class="page-content">
			<div class="row">
				<div class="col-sm-12"></div>
			</div>
			<section>
				<!--Panel-->
				<div class="card w-100 query-panel">
					<div class="card-body">
						<div class="row">
							<div class="col-sm-12 query-table-loader">
								<nav>
									<div class="nav nav-tabs" id="nav-tab" role="tablist">
										<a class="nav-item nav-link active" id="nav-home-tab"
											data-toggle="tab" href="#nav-home" role="tab"
											aria-controls="nav-home" aria-selected="true">Main</a> <a
											class="nav-item nav-link" id="nav-profile-tab"
											data-toggle="tab" href="#nav-profile" role="tab"
											aria-controls="nav-profile" aria-selected="false">Freeze</a>
										<a class="nav-item nav-link" id="nav-contact-tab"
											data-toggle="tab" href="#nav-contact" role="tab"
											aria-controls="nav-contact" aria-selected="false">Matching</a>
											<a class="nav-item nav-link" id="nav-report-tab"
											data-toggle="tab" href="#nav-report" role="tab"
											aria-controls="nav-contact" aria-selected="false">Report</a>
									</div>
								</nav>
								<div class="tab-content" id="nav-tabContent">
									<div class="tab-pane fade show active" id="nav-home"
										role="tabpanel" aria-labelledby="nav-home-tab">
										<div class="query-table-container pr-0 mb-2">
											<form id="searchParametersForm" method="post" action="main">
												<section class="custom-section">
													<label class="section-label">BRS Type</label>
													<div class="row" style="margin-top: -30px">
														<div class="col">
															<div class="form-group">
																<div class="form-check form-check-inline">
																	<input type="radio" class="form-check-input"
																		id="matchingType1" name="matchingType" value="O"
																		required data-param1="O" data-param2="B"> <label
																		class="form-check-label" for="matchingType1">Book
																		V/S Bank</label>
																</div>
																<div class="form-check form-check-inline">
																	<input type="radio" class="form-check-input"
																		id="matchingType2" name="matchingType" value="B"
																		data-param1="B" data-param2="O"> <label
																		class="form-check-label" for="matchingType2">
																		Bank V/S Book</label>
																</div>
																<div class="form-check form-check-inline">
																	<input type="radio" class="form-check-input"
																		id="matchingType3" name="matchingType" value="B"
																		data-param1="B" data-param2="B"> <label
																		class="form-check-label" for="matchingType3">
																		Bank V/S Bank</label>
																</div>
																<div class="form-check form-check-inline">
																	<input type="radio" class="form-check-input"
																		id="matchingType4" name="matchingType" value="O"
																		data-param1="O" data-param2="O"> <label
																		class="form-check-label" for="matchingType4">
																		Book V/S Book</label>
																</div>
															</div>
														</div>
													</div>
												</section>
												<section class="custom-section">
													<label class="section-label">Search <span
														class="brsParam"></span> Parameters
													</label>
													<div class="row mb-2" style="margin-top: -30px">
														<div class="col-sm-12">
															<div class="row mb-2">
																<div class="col">
																	<label>Bank Code</label>
																	<div class="form-group">
																		<select id="bankCode" name="bankCode"
																			class="form-control" required>
																			<option value=" ">--Select--</option>
																			<c:forEach var="item" items="${partnerBankList}">
																				<option value="${item}">${item}</option>
																			</c:forEach>

																		</select>
																	</div>
																</div>
																<div class="col">
																	<label>From Date</label>
																	<div class="form-group">
																		<div class="input-group">
																			<input type="text"
																				class="form-control form-control-sm date"
																				name="datetimepickerFrom" id="datetimepickerFrom"
																				placeholder="From Date" aria-label="From Date"
																				aria-describedby="datepicker-addon"
																				autocomplete="off">
																			<div class="input-group-append">
																				<span class="input-group-text" id="datepicker-addon">
																					<i class="fa fa-calendar"></i>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col">
																	<label>To Date</label>
																	<div class="form-group">
																		<div class="input-group">
																			<input type="text"
																				class="form-control form-control-sm date"
																				name="datetimepickerTo" id="datetimepickerTo"
																				placeholder="To Date" aria-label="To Date"
																				aria-describedby="datepicker-addon"
																				autocomplete="off">
																			<div class="input-group-append">
																				<span class="input-group-text" id="datepicker-addon">
																					<i class="fa fa-calendar"></i>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col">
																	<label>Tran Type</label>
																	<div>
																		<div class="form-group">
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="tranTypeD" name="tranType" value="D" required>
																				<label class="form-check-label" for="tranTypeD">DR</label>
																			</div>
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="tranTypeC" name="tranType" value="C"> <label
																					class="form-check-label" for="tranTypeC">CR</label>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col d-none">
																	<label>Order By</label>
																	<div class="form-group">
																		<select class="form-control" name="orderBy"
																			id="orderBy">
																			<option value="">--Select--</option>
																			<option value="bro_flex_15">Receipt</option>
																			<option value="bro_doc_dt">Date</option>
																			<option value="bro_chq_no">Cheque no</option>
																			<option value="bro_flex_13">Loan no</option>
																			<option value="bro_flex_27">Payment Mode</option>
																		</select>
																	</div>
																</div>
																<div class="col">
																	<label>Payment Mode</label>
																	<div class="form-group">
																		<select class="form-control" name="pMode" id="pMode">
																			<option value="">--Select--</option>

																			<c:forEach var="item" items="${payModeList}">
																				<option value="${item}">${item}</option>
																			</c:forEach>

																		</select>
																	</div>
																</div>
																<div class="col d-none">
																	<label>Descending</label>
																	<div>
																		<div class="form-group">
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="des1" name="desc" value="yes"> <label
																					class="form-check-label" for="des1">Yes</label>
																			</div>
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="des2" name="desc" value="no"> <label
																					class="form-check-label" for="des2">No</label>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col">
																	<div class="btn-group btn-group-sm" role="group"
																		aria-label="Basic example">
																		<button type="button"
																			class="btn btn-primary btn-sm mr-1"
																			id="searhParamBtn">
																			<i class="fa fa-search"></i> Search
																		</button>
																		<button type="button"
																			class="btn btn-primary btn-sm mr-1"
																			id="resetSearchParamBtn">
																			<i class="fa fa-refresh"></i> Refresh
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</section>
											</form>
										</div>
										<section class="custom-section">
											<label class="section-label">Search <span
												class="brsParam"></span> Results
											</label>
											<div class="paramTable1">
												<%@ include file="matchingTable.jsp"%>
											</div>
										</section>
									</div>
									<div class="tab-pane fade" id="nav-profile" role="tabpanel"
										aria-labelledby="nav-profile-tab">
										<section class="custom-section">
											<label class="section-label">Search <span
												class="brsParam1"></span> Results
											</label>
											<div class="row" style="margin-top: -30px">
												<div class="col-lg-12">
													<button class="btn btn-sm btn-primary d-none" id="loadFreezeTable">
														<i class="fa fa-search"></i> Search <span class="brsParam1"></span> Results
													</button>
												</div>
											</div>
											<div class="paramTable2">
												<%@ include file="freezeTable.jsp"%>
											</div>
										</section>
									</div>
									<div class="tab-pane fade" id="nav-contact" role="tabpanel"
										aria-labelledby="nav-contact-tab">
										<section class="custom-section">
											<label class="section-label">Verify <span
												class="brsParam"></span> VS <span class="brsParam1"></span>
												Matching Results
											</label>
											<div class="row" style="margin-top: -30px">
												<div class="col-lg-12">
													<button class="btn btn-sm btn-primary d-none"
														id="loadMatchingResults"
														title="Please Search Main Screen First">Check
														Matching</button>
													<button class="btn btn-sm btn-primary" id="submitMatching"><i class="fa fa-upload"></i> Submit
														Matching</button>
												</div>
											</div>
											<div class="displayTable1">
												<section class="custom-section">
													<label class="section-label">Check <span
														class="brsParam"></span> Matching Results
													</label>
													<table id="freezeTable1"
														class="table table-striped table-hover dataTable no-footer table-responsive mt-2"
														width="100%" style="margin-top: -30px;">
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
															<tr>
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
																<td></td>
																<td></td>
															</tr>
														</tbody>
													</table>
												</section>
												<section class="custom-section">
													<label class="section-label">Check <span
														class="brsParam1"></span> Matching Results
													</label>
													<table id="freezeTable2"
														class="table table-striped table-hover dataTable no-footer table-responsive mt-2"
														width="100%" style="margin-top: -30px;">
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
															<tr>
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
																<td></td>
																<td></td>
															</tr>
														</tbody>
													</table>
												</section>
											</div>
										</section>

									</div>
									<div class="tab-pane fade" id="nav-report" role="tabpanel" aria-labelledby="nav-report-tab">
										<form id="reportGenerateForm">										
												<section class="custom-section">
													<label class="section-label">Report <span
														class="brsParam"></span> Parameters
													</label>
													<div class="row mb-2" style="margin-top: -30px">
														<div class="col-sm-12">
															<div class="row mb-2">
																<div class="col">
																<label>Select Report Type</label>
																	<div>
																		<div class="form-group">
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="reportType1" name="reportType" value="OPEN"
																					required> <label
																					class="form-check-label" for="reportType1">Open</label>
																			</div>
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="reportType2" name="reportType" value="FULL"> 
																					<label
																					class="form-check-label" for="reportType2">
																					Full</label>
																			</div>
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="reportType3" name="reportType" value="CASH"> 
																					<label
																					class="form-check-label" for="reportType3">
																					Cash</label>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col">
																	<label>Bank Code</label>
																	<div class="form-group">
																		<select id="bankCodeRept" name="bankCodeRept"
																			class="form-control" required>
																			<option value=" ">--Select--</option>
																			<c:forEach var="item" items="${partnerBankList}">
																				<option value="${item}">${item}</option>
																			</c:forEach>

																		</select>
																	</div>
																</div>
																<div class="col">
																	<label>From Date</label>
																	<div class="form-group">
																		<div class="input-group">
																			<input type="text"
																				class="form-control form-control-sm date"
																				name="datetimepickerFromRept" id="datetimepickerFromRept"
																				placeholder="From Date" aria-label="From Date"
																				aria-describedby="datepicker-addon"
																				autocomplete="off">
																			<div class="input-group-append">
																				<span class="input-group-text" id="datepicker-addon">
																					<i class="fa fa-calendar"></i>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col">
																	<label>To Date</label>
																	<div class="form-group">
																		<div class="input-group">
																			<input type="text"
																				class="form-control form-control-sm date"
																				name="datetimepickerToRept" id="datetimepickerToRept"
																				placeholder="To Date" aria-label="To Date"
																				aria-describedby="datepicker-addon"
																				autocomplete="off">
																			<div class="input-group-append">
																				<span class="input-group-text" id="datepicker-addon">
																					<i class="fa fa-calendar"></i>
																				</span>
																			</div>
																		</div>
																	</div>
																</div>																																												
															</div>
															<div class="row">
																<div class="col">
																	<div class="btn-group btn-group-sm" role="group"
																		aria-label="Basic example">
																		<button type="button"
																			class="btn btn-primary btn-sm mr-1"
																			id="searhReportBtn">
																			<i class="fa fa-search"></i> Generate
																		</button>
																		<button type="button"
																			class="btn btn-primary btn-sm mr-1"
																			id="resetReportBtn">
																			<i class="fa fa-refresh"></i> Refresh
																		</button>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</section>
												<section class="custom-section">
													<label class="section-label">Report Result
													</label>
													<div class="reportTable">
														<%@ include file="reportTable.jsp"%>
													</div>
												</section>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--/.Panel-->
			</section>
		</div>
		<!-- Notification -->
		<div class="notification">
			<i class="fa fa-check fa-fw text-success"></i><span class="msg"></span>
		</div>
		<!-- Notification -->
	</div>
	<!-- Page content -->


	<!-- Add the all the modals below this comment -->


	<div class="lichfl-ajax-overlay">
		<i class="fa fa-spinner fa-spin"></i>
	</div>


	<!-- Optional JavaScript -->
	<script src="resources/js/custom.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var matchTable, freezeTable,freezeTable1,freezeTable2;
							$('#freezeTable1,#freezeTable2').DataTable({
								dom : 't'
							});

							$('[data-toggle="tooltip"]').tooltip();

							$.sessionTimeout({
								keepAliveUrl : 'resources/keep.html',
								keepAliveInterval : 30000000,
								ajaxType : 'GET',
								warnAfter : 30000000,
								redirAfter : 31000000,
								onWarn : function() {
									$('#modalSessionTimout').modal('show');
								},
								onRedir : function() {
									logout();
								}
							});

							window.onbeforeunload = function(evnt) {
								logout();
							};

							$('#datetimepickerFrom').datetimepicker({
								format : 'DD/MM/YYYY',
								maxDate : moment()
							});
							$('#datetimepickerTo').datetimepicker({
								format : 'DD/MM/YYYY',
								maxDate : moment()
							});
							$('#datetimepickerFromRept').datetimepicker({
								format : 'DD/MM/YYYY',
								maxDate : moment()
							});
							$('#datetimepickerToRept').datetimepicker({
								format : 'DD/MM/YYYY',
								maxDate : moment()
							});
							//$('#bankCode').select2();

							$('input[type="radio"][name="matchingType"]')
									.change(
											function() {
												var param1 = $(
														'input[type="radio"][name="matchingType"]:checked')
														.attr('data-param1'), param2 = $(
														'input[type="radio"][name="matchingType"]:checked')
														.attr('data-param2')
												if (param1 == 'O') {
													$('.brsParam').html('Book');
													if (param2 == 'B')
														$('.brsParam1').html(
																'Bank');
													else
														$('.brsParam1').html(
																'Book');
												} else {
													$('.brsParam').html('Bank');
													if (param2 == 'B')
														$('.brsParam1').html(
																'Bank');
													else
														$('.brsParam1').html(
																'Book');
												}
											});

							$('#searchParametersForm')
									.bootstrapValidator(
											{
												trigger : 'blur',
												fields : {
													bankCode : {
														validators : {
															notEmpty : {
																message : 'This field is required'
															}

														}
													},
													datetimepickerFrom : {
														validators : {
															notEmpty : {
																message : 'This field is required'
															},
															date : {
																max : 'datetimepickerTo',
																format : 'DD/MM/YYYY',
																message : 'From date should be less than To date'
															}
														}
													},
													datetimepickerTo : {
														validators : {
															notEmpty : {
																message : 'This field is required'
															},
															date : {
																min : 'datetimepickerFrom',
																format : 'DD/MM/YYYY',
																message : 'To Date should be greater than from date'
															}
														}
													},
												},
											});
						});

		$('#datetimepickerFrom').change(
				function() {
					$('#searchParametersForm').data('bootstrapValidator')
							.revalidateField('datetimepickerFrom');
				});

		$('#datetimepickerTo').change(
				function() {
					$('#searchParametersForm').data('bootstrapValidator')
							.revalidateField('datetimepickerTo');
				});
		
		$('#datetimepickerFromRept').change(
				function() {
					$('#reportGenerateForm').data('bootstrapValidator')
							.revalidateField('datetimepickerFromRept');
				});

		$('#datetimepickerToRept').change(
				function() {
					$('#reportGenerateForm').data('bootstrapValidator')
							.revalidateField('datetimepickerToRept');
				});

		$('#bankCode').change(
				function() {
					$('#searchParametersForm').data('bootstrapValidator')
							.revalidateField('bankCode');
				});
		
		$('#bankCodeRept').change(
				function() {
					$('#reportGenerateForm').data('bootstrapValidator')
							.revalidateField('bankCodeRept');
				});

		$('#searhParamBtn').click(
				function() {
					$('#searchParametersForm').bootstrapValidator().off(
							'success.form.bv').on('success.form.bv',
							function(e) {
								$('.lichfl-ajax-overlay').show();
								var data = new FormData(searchParametersForm);
								console.log(data);
								$.ajax({
									url : 'getBookRec',
									type : 'post',
									cache : false,
									data : data,
									processData : false,
									contentType : false,
									success : function(data) {
										$('.paramTable1').html(data);
										//$('#loadFreezeTable').removeAttr('disabled');
										//$('#loadFreezeTable').removeAttr('title');
										$('.lichfl-ajax-overlay').hide();
									},
									error : function(e) {
										console.log(e);
										$('.lichfl-ajax-overlay').hide();
									}
								});
							});
					$('#searchParametersForm').bootstrapValidator('validate');
				});

		$('#nav-profile-tab').click(				
				function() {
					if(matchTable.rows().count() === 0){
						bootbox.alert({
							title: "<i class='fa fa-exclamation-triangle'></i> Warning",
							message: "Please search main screen data",
							buttons: { ok: { className: "btn-sm btn-primary", label: '<i class="fa fa-check"></i> Ok' }}
						});
					}
					else{
						$('.lichfl-ajax-overlay').show();
						var data = new FormData(searchParametersForm);
						var param2 = $(
								'input[type="radio"][name="matchingType"]:checked')
								.attr('data-param2');
						var tranType = data.get('tranType');
						if (tranType == 'D') {
							tranType = 'C'
						} else {
							tranType = 'D'
						}
						data.set('tranType', tranType);
						data.set('matchingType', param2);
						$.ajax({
							url : 'getFreezeRecords',
							type : 'post',
							cache : false,
							data : data,
							processData : false,
							contentType : false,
							success : function(data) {
								$('.paramTable2').html(data);
								//$('#loadFreezeTable').removeAttr('disabled');
								//$('#loadFreezeTable').removeAttr('title');
								$('.lichfl-ajax-overlay').hide();
							},
							error : function(e) {
								console.log(e);
								$('.lichfl-ajax-overlay').hide();
							}
						});
					}
				});

		$('#resetSearchParamBtn').click(function() {
			document.getElementById("searchParametersForm").reset();
			$('#searchParametersForm').bootstrapValidator("resetForm", true);
		});

		$('#nav-contact-tab').click(function() {
			var a = matchTable.rows({
				selected : true
			}).data();
			console.log(a[0]);
			
			freezeTable1 = $('#freezeTable1').DataTable({
				destroy : true,
				columnDefs : [ {
					target : 0,
					visible : false,
					searchable : false
				}, ],
				dom : 't',
				data : a
			}).draw();

			var b = freezeTable.rows({
				selected : true
			}).data();
			
			freezeTable2 = $('#freezeTable2').DataTable({
				destroy : true,
				columnDefs : [ {
					target : 0,
					visible : false,
					searchable : false
				}, ],
				dom : 't',
				data : b
			}).draw();
			
		})

		function logout() {
			$('.lichfl-ajax-overlay').show();
			$.ajax({
				url : 'logout',
				type : 'post',
				cache : false,
				data : '',
				processData : false,
				contentType : false,
				success : function(data) {
					window.location.href = 'login.jsp';
					$('.lichfl-ajax-overlay').hide();
				},
				error : function(e) {
					console.log(e);
					$('.lichfl-ajax-overlay').show();
				}
			});
		}
		
		$('#submitMatching').click(function(){
			if (freezeTable1.rows().count() === 0){
				bootbox.alert({
					title: "<i class='fa fa-exclamation-triangle'></i> Warning",
					message: "Please select main screen data",
					buttons: { ok: { className: "btn-sm btn-primary", label: '<i class="fa fa-check"></i> Ok' }}
				});
			}
			else if(freezeTable2.rows().count() === 0){
				bootbox.alert({
					title: "<i class='fa fa-exclamation-triangle'></i> Warning",
					message: "Please select freeze screen data",
					buttons: { ok: { className: "btn-sm btn-primary", label: '<i class="fa fa-check"></i> Ok' }}
				});
			}
			else{
			bootbox.confirm({
				title: "<i class='fa fa-info-circle'></i> Submit Manual Matching",
				message: "Do you want to Submit manual matching. Please verify before submittig the data.",
				buttons: { cancel: { className: "btn-sm btn-default", label: '<i class="fa fa-times"></i> Cancel' }, confirm: { className: "btn-sm btn-primary", label: '<i class="fa fa-check"></i> Confirm' } },
				callback: function(result) {
					console.log(result);
					if(result == true){						
						
							var broKey = [];
							var matchingForm = new FormData();
							var matchkey = freezeTable1.column(1).data()[0];		
							var b = freezeTable2.column(1).data();
							var c = freezeTable2.column(11).data();
							
							for (var i = 0; i<= b.length-1; i++){
								broKey.push({'matchkey':matchkey,'brokey':b[i],'amount':c[i]});							
							}				
							broKey = JSON.stringify(broKey);
							console.log('broKey :: '+broKey);
							matchingForm.append('brokey',broKey);
							$.ajax({
								url : 'submitMatchData',
								type : 'post',
								cache : false,
								processData : false,
								contentType: false,
								data : matchingForm,
								success : function(data) {
									console.log(data)
									if(data.message = "success"){
										bootbox.alert({
											title: "<i class='fa fa-check'></i> Success",
											message: 'Data submitted successfully',
											buttons: { ok: { className: "btn-sm btn-primary", label: '<i class="fa fa-check"></i> Ok' }},
											callback: function(result){
												matchTable.clear().draw();
												freezeTable.clear().draw();
												freezeTable1.clear().draw();
												freezeTable2.clear().draw();
												$('#resetSearchParamBtn').click();
												$('#nav-home-tab').click();
											}
										});	
									}									
								},
								error : function(e) {		
										console.log(e)
										bootbox.alert({
											title: "<i class='fa fa-times-circle text-error'></i> Error",
											message: e.responseJSON.message,
											buttons: { ok: { className: "btn-sm btn-primary", label: '<i class="fa fa-check"></i> Ok' }}
										});
								}
							});
							
					}
				},
			});
			}
		});
		
		$('#reportGenerateForm')
		.bootstrapValidator(
				{
					trigger : 'blur',
					fields : {
						bankCodeRept : {
							validators : {
								notEmpty : {
									message : 'This field is required'
								}

							}
						},
						datetimepickerFromRept : {
							validators : {
								notEmpty : {
									message : 'This field is required'
								},
								date : {
									max : 'datetimepickerToRept',
									format : 'DD/MM/YYYY',
									message : 'From date should be less than To date'
								}
							}
						},
						datetimepickerToRept : {
							validators : {
								notEmpty : {
									message : 'This field is required'
								},
								date : {
									min : 'datetimepickerFromRept',
									format : 'DD/MM/YYYY',
									message : 'To Date should be greater than from date'
								}
							}
						},
					},
				});

		$('#searhReportBtn').click(
				function() {
					$('#reportGenerateForm').bootstrapValidator().off(
							'success.form.bv').on('success.form.bv',
							function(e) {
								$('.lichfl-ajax-overlay').show();
								var data = new FormData(reportGenerateForm);
								console.log(data);
								$.ajax({
									url : 'submitReport',
									type : 'post',
									cache : false,
									data : data,
									processData : false,
									contentType : false,
									success : function(data) {
										$('.lichfl-ajax-overlay').hide();
									},
									error : function(e) {
										console.log(e);
										$('.lichfl-ajax-overlay').hide();
									}
								});
							});
					$('#reportGenerateForm').bootstrapValidator('validate');
				});
	</script>


</body>
</html>