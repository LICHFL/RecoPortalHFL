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
<link rel="stylesheet"
	href="resources/vendor/select2/css/select2.min.css">
<link rel="stylesheet"
	href="resources/vendor/select2/css/select2-bootstrap.min.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="icon" href="resources/images/lichfl-favicon.png">
<title>LICHFL - Feedback Client</title>

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
	<script  src="resources/vendor/moment/moment.min.js"></script>
	<!-- Date time picker -->
	<script 
		src="resources/vendor/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<!-- Select 2 -->
	<script  src="resources/vendor/select2/js/select2.full.min.js"></script>
	<script 
		src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
	<script 
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
	<script 
		src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
	<script 
		src="https://cdn.datatables.net/fixedcolumns/4.3.0/js/dataTables.fixedColumns.min.js"></script>
</head>

<body>
	<!-- Page wrapper -->
	<div class="page-wrapper" id="page-wrapper">
		<!-- Navbar -->
		<nav class="navbar navbar-expand-md navbar-light mb-2 no-content">
			<div class="web-page-title">BRS</div>
			<ul class="navbar-nav ml-auto nav-flex-icons">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fa fa-user"></i>
				</a>
					<div class="dropdown-menu dropdown-menu-right dropdown-default"
						aria-labelledby="navbarDropdownMenuLink-333">
						<a class="dropdown-item" href="#">Logout</a>
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
															<div class="form-check form-check-inline">
																<input type="radio" class="form-check-input"
																	id="matchingType1" name="matchingType" value="O"
																	required> <label class="form-check-label"
																	for="matchingType1">Book V/S Bank</label>
															</div>
															<div class="form-check form-check-inline">
																<input type="radio" class="form-check-input"
																	id="matchingType2" name="matchingType" value="mt2">
																<label class="form-check-label" for="matchingType2">
																	Bank V/S Book</label>
															</div>
															<div class="form-check form-check-inline">
																<input type="radio" class="form-check-input"
																	id="matchingType3" name="matchingType" value="mt3">
																<label class="form-check-label" for="matchingType3">
																	Bank V/S Bank</label>
															</div>
															<div class="form-check form-check-inline">
																<input type="radio" class="form-check-input"
																	id="matchingType4" name="matchingType" value="mt4">
																<label class="form-check-label" for="matchingType4">
																	Book V/S Book</label>
															</div>
														</div>
													</div>
												</section>
												<section class="custom-section">
													<label class="section-label">Search <span
														class="brsParam">Book</span> Parameters
													</label>
													<div class="row mb-2" style="margin-top: -30px">
														<div class="col-sm-12">
															<div class="row mb-2">
																<div class="col">
																	<label>Bank Code</label>
																	<div class="form-group">
																		<select id="bankCode" name="bankCode"
																			class="form-control" required>
																			<option value="">--Select--</option>
																			<option value="LUHDFCCMS1">LUHDFCCMS1</option>
																			<option>MAHDFCCMS1</option>
																			<option>MAHDFCCMS3</option>
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
																					id="tranTypeD" name="tranType" value="D"
																					required> <label class="form-check-label"
																					for="tranTypeD">DR</label>
																			</div>
																			<div class="form-check form-check-inline">
																				<input type="radio" class="form-check-input"
																					id="tranTypeC" name="tranType" value="C">
																				<label class="form-check-label" for="tranTypeC">CR</label>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col">
																	<label>Order By</label>
																	<div class="form-group">
																		<select class="form-control"></select>
																	</div>
																</div>
																<div class="col">
																	<label>Payment Mode</label>
																	<div class="form-group">
																		<select class="form-control"></select>
																	</div>
																</div>
																<div class="col">
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
																			class="btn btn-primary btn-sm mr-1">
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
												class="brsParam">Book</span> Results
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
												class="brsParam">Book</span> Results
											</label>
											<div class="paramTable2">											
												<%@ include file="freezeTable.jsp"%>
											</div>											
										</section>
									</div>
									<div class="tab-pane fade" id="nav-contact" role="tabpanel"
										aria-labelledby="nav-contact-tab">...</div>
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
        $(document).ready(function() {
            $('[data-toggle="tooltip"]').tooltip();

            $.sessionTimeout({
                keepAliveUrl: 'resources/keep.html',
                keepAliveInterval: 300000,
                ajaxType: 'GET',
                warnAfter: 300000,
                redirAfter: 310000,
                onWarn: function() {
                    $('#modalSessionTimout').modal('show');
                },
                onRedir: function() {
                    logout();
                }
            });

            window.onbeforeunload = function(evnt) {
                logout();
            };
            
            $('#datetimepickerFrom').datetimepicker({
                format: 'DD/MM/YYYY',
                maxDate: moment()
            });
            $('#datetimepickerTo').datetimepicker({
                format: 'DD/MM/YYYY',
                maxDate: moment()
            });
            $('#bankCode').select2();

            $('input[type="radio"][name="matchingType"]').change(function() {
                if (this.value == 'mt1' || this.value == 'mt4') {
                    $('.brsParam').html('Book')
                } else {
                    $('.brsParam').html('Bank')
                }
            });
            
            $('#searchParametersForm').bootstrapValidator({
            	trigger: 'blur',
    			fields : {
    				bankCode : {
    					validators : {
    						notEmpty : {
    							message : 'This field is required'
    						}

    					}
    				},
    				datetimepickerFrom: {
    		            validators: {
    		                notEmpty: {
    		                    message: 'This field is required'
    		                },
    		                date: {
    		                    max: 'datetimepickerTo',
    		                    format: 'DD/MM/YYYY',
    		                    message: 'From date should be less than To date'
    		                }
    		            }
    		        },
    		        datetimepickerTo: {
    		            validators: {
    		                notEmpty: {
    		                    message: 'This field is required'
    		                },
    		                date: {
    		                    min: 'datetimepickerFrom',
    		                    format: 'DD/MM/YYYY',
    		                    message: 'To Date should be greater than from date'
    		                }
    		            }
    		        },
    			},
    		});
        });
        
        $('#datetimepickerFrom').change(function(){
        	$('#searchParametersForm').data('bootstrapValidator').revalidateField('datetimepickerFrom');
        });
        
        $('#datetimepickerTo').change(function(){
        	$('#searchParametersForm').data('bootstrapValidator').revalidateField('datetimepickerTo');
        });
        
        $('#bankCode').change(function(){
        	$('#searchParametersForm').data('bootstrapValidator').revalidateField('bankCode');
        });
        
        $('#searhParamBtn').click(function(){        	
            $('#searchParametersForm').bootstrapValidator().off('success.form.bv').on('success.form.bv', function(e) {
            	var data = new FormData(searchParametersForm);
            	console.log(data);
        	    $.ajax({
        	        url: 'getBookRec',
        	        type: 'post',
        	        cache: false,
        	        data: data,
        	       processData: false,
        	        contentType: false,
        	        success: function(data) {
        	        	console.log(data)
        	        	$('.paramTable1').html(data);
        	        },
        	    	error: function(e) {
                		console.log(e)
                	}
        	    });
        	});
        	$('#searchParametersForm').bootstrapValidator('validate');
        })
  
    </script>
</body>
</html>