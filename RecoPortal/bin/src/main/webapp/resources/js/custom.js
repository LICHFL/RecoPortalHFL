/**
	Purpose : File Attachment (Add and Reopen query)- Read the uploaded file and display the name of the file before upload, with option to clear attachment.
**/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var fileUploadExtension = input.files[0].name.split('.').pop().toLowerCase();
        var fileUploadName = input.files[0].name;
        reader.onload = function(e) {
            $('#uploadedAttachment').find('.upload-file-info').html(fileUploadName);
            $('#uploadedAttachment').attr('title', fileUploadName);
            $('#uploadedAttachment').addClass('uploaded');
            $('.clear-attachment').show();
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function readReopenURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var fileUploadExtension = input.files[0].name.split('.').pop().toLowerCase();
        var fileUploadName = input.files[0].name;
        reader.onload = function(e) {
            $('#uploadedReopenAttachment').find('.upload-file-info').html(fileUploadName);
            $('#uploadedReopenAttachment').attr('title', fileUploadName);
            $('#uploadedReopenAttachment').addClass('uploaded');
            $('.clear-reopen-attachment').show();
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function clearReopenFile() {
    $('#uploadedReopenAttachment .upload-file-info').html('');
    $('#uploadedReopenAttachment').removeClass('uploaded');
    $('#uploadedReopenAttachment').removeAttr('title');
    $('#queryReopenAttachment').val('');
    $('#queryReopenForm').data('bootstrapValidator').revalidateField('reopenimportFile');
    $('.clear-reopen-attachment').hide();
    
}

function clearFile() {
    $('#uploadedAttachment .upload-file-info').html('');
    $('#uploadedAttachment').removeClass('uploaded');
    $('#uploadedAttachment').removeAttr('title');
    $('#queryAttachment').val('');
    $('#add-query-form').data('bootstrapValidator').revalidateField('importFile');
    $('.clear-attachment').hide();
    
}

$('.clear-attachment').click(clearFile);
$('.clear-reopen-attachment').click(clearReopenFile);

$('#queryAttachment').change(function(){
	$('#add-query-form').data('bootstrapValidator').revalidateField('importFile');
});

$('#queryReopenAttachment').change(function(){
	$('#queryReopenForm').data('bootstrapValidator').revalidateField('reopenimportFile');
});
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : Side Navigation- Animate the icon on hover
**/
$(".nav-horizontal").mouseenter(function() {
    $(this).find('.nav-icon').addClass('jackInTheBox animated');
});

$(".nav-horizontal").mouseleave(function() {
    $(this).find('.nav-icon').removeClass('jackInTheBox animated');
});
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : 
**/
function displayRecords(data, closeQuery) {
    $('#query-container tr').remove();
    if (!data || !Array.isArray(data)) return;
    data.forEach(function(d) {
        var row = $('<tr>'),
            cell = function(t, at) {
                var c = $('<td>');
                c.text(t);
                if (at) c.attr(at);
                return c;
            }, link = $('<a>', {
                'href': 'javascript:void(0);',
                'onclick': 'javascript:viewQuery(\'' + d.id + '\');'
            }),
            firstCell = $('<td>');

        link.text(d.id);
        firstCell.append(link);

        row.append(firstCell);
        row.append(cell(d.module));
        row.append(cell(d.subModule));
        row.append(cell(d.type));
        row.append(cell(d.typeInput));
        row.append(cell(d.openDate));
        row.append(cell(d.closeDate, {
            'class': 'gClose'
        }));

        if (d.assignedToEmail && !closeQuery) {
/*            
			var mailTo = $('<a>', {
                'href': 'mailto:' + d.assignedToEmail + '?subject=Feedback Portal: Query # : ' + d.id,
                'title': 'Mail To: ' + d.assignedTo,
                'class': 'assigned-to-mail'
            });
            mailTo.html('<i class="fa fa-envelope-o fa-fw"></i>' + d.assignedTo);
*/          
        	var assignedToCell = $('<td>');
            assignedToCell.append(d.assignedTo);
            if (d.assignedToMobile) {
                var mobile = $('<div>', {'class':'assigned-to-mbl'});
                mobile.html('<i class="fa fa-mobile-phone"></i>' + d.assignedToMobile);
                assignedToCell.append(mobile);
            }
            row.append(assignedToCell);
        } else {
            row.append(cell(d.assignedTo));
        }

        row.append(cell(d.status));
        table.row.add(row).draw(false);
    });
    $('#queries-count').text(data.length);
    if (closeQuery) {
        $('.gClose').show();
    } else {
        $('.gClose').hide();
    }
    setTimeout(function() {
        $.fn.dataTable
            .tables({
                visible: true,
                api: true
            })
            .columns.adjust();
    }, 25);
    $('.query-table-container').show();
    $('.query-table-loader').hide();
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

function checkValidResp(data) {
	if ((data.hasOwnProperty("redirectlogin") && data.redirectlogin == 'Y') 
			|| (typeof data == 'string' && data.indexOf('Login') != -1)) {
		var message = data.hasOwnProperty("message") ? data.message : 'Your session got expired. Please login again!';
		notification(message);
		location.href = "";
		return false;
	} else if (typeof data == 'object' && data.hasOwnProperty("error") && data.error == 'Y') {
		notification(data.message);
		return false;
	}
	return true;
}

/**
	Purpose : 
**/
function loadClosedQueries() {
    $(".lichfl-ajax-overlay").show();
    table.clear().draw();
    table.columns.adjust()
    $('.closed-query').addClass('btn-light-blue').removeClass('btn-blue-grey');
    $('.open-query').addClass('btn-blue-grey').removeClass('btn-light-blue');
	$('.unassigned-query').addClass('btn-blue-grey').removeClass('btn-light-blue');
    //$('.query-panel .card-header').addClass('blue-grey').removeClass('light-blue');
    $('.query-panel .card-header .query-title').html('<i class="fa fa-folder fa-fw"></i> Closed Queries');
    $('#refresh-button').removeAttr('onclick');
    $('#refresh-button').attr('onclick', 'javascript:loadClosedQueries();');
    $('.query-table-container').hide();
    $('.query-table-loader').show();
	$(".lichfl-ajax-overlay").hide();
    /*$.ajax({
        url: "closed",
        type: "GET",
        success: function(data) {
        	$(".lichfl-ajax-overlay").hide();
        	if (checkValidResp(data)) {
                displayRecords(data, true);
        	}
        }
    });*/
}

function loadOpenQueries() {
    $(".lichfl-ajax-overlay").show();
    table.clear().draw();
    $('.closed-query').addClass('btn-blue-grey').removeClass('btn-light-blue');
    $('.open-query').addClass('btn-light-blue').removeClass('btn-blue-grey');
	$('.unassigned-query').addClass('btn-blue-grey').removeClass('btn-light-blue');
    //$('.query-panel .card-header').addClass('blue-grey').removeClass('light-blue');
    $('.query-panel .card-header .query-title').html('<i class="fa fa-folder-open fa-fw"></i> Open Queries');
    $('#refresh-button').removeAttr('onclick');
    $('#refresh-button').attr('onclick', 'javascript:loadOpenQueries();');
    $('.query-table-container').hide();
    $('.query-table-loader').show();
	$(".lichfl-ajax-overlay").hide();
   /* $.ajax({
        url: "open",
        type: "GET",
        success: function(data) {
        	$(".lichfl-ajax-overlay").hide();
        	if (checkValidResp(data)) {
                displayRecords(data, false);
        	}
        }
    });*/
}

function loadUnassignedQueries() {
    $(".lichfl-ajax-overlay").show();
    table.clear().draw();
    $('.closed-query').addClass('btn-blue-grey').removeClass('btn-light-blue');
    $('.open-query').addClass('btn-blue-grey').removeClass('btn-light-blue');
	$('.unassigned-query').addClass('btn-light-blue').removeClass('btn-blue-grey');
    //$('.query-panel .card-header').addClass('blue-grey').removeClass('light-blue');
    $('.query-panel .card-header .query-title').html('<i class="fa fa-exclamation-triangle fa-fw"></i> Unassigned Queries');
    $('#refresh-button').removeAttr('onclick');
    $('#refresh-button').attr('onclick', 'javascript:loadUnassignedQueries();');
    $('.query-table-container').hide();
    $('.query-table-loader').show();
	$(".lichfl-ajax-overlay").hide();
   /* $.ajax({
        url: "open",
        type: "GET",
        success: function(data) {
        	$(".lichfl-ajax-overlay").hide();
        	if (checkValidResp(data)) {
                displayRecords(data, false);
        	}
        }
    });*/
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : Side Navigation- Logout action
**/
function logout() {
    $('#logout-form').submit();
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : 
**/
function populateOptions(field, options) {
    field.find('option').remove();
    var defaultOpt = $('<option>', {
        'value': ''
    });
    defaultOpt.text('Select');
    field.append(defaultOpt);
    if (Array.isArray(options)) {
        options.forEach(function(o) {
            var option = $('<option>', {
                'value': o.id
            });
            option.text(o.description);
            field.append(option);
        });
    }
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : 
**/
$("#queryDisplay-modal").on("hide.bs.modal", function () {	
	$('#add-query-form').bootstrapValidator('resetForm', true);
});
function addQuery() {	
	$(".lichfl-ajax-overlay").show();
	$.ajax({
        url: "lov/load",
        type: "GET",
        success: function(resp) {
        	$(".lichfl-ajax-overlay").hide();
        	if (checkValidResp(resp)) {
                //Reset Form
                $('#add-query-form').find("input[type=text], textarea").val('');
                $('#add-query-form').find("select").val('');
                clearFile();            
                $('.add-query').removeClass('d-none');
                $('.view-query-info').addClass('d-none');
                populateOptions($('#add-modules'), resp.modules);
                populateOptions($('#add-submodules'), null);
                populateOptions($('#add-type'), resp.types);
                populateOptions($('#add-qry-type'), resp.qrytypes);
                $('.empcode').val($('#username').val());
                $('#queryDisplay-modal .modal-title').html('<i class="fa fa-plus-circle fa-fw"></i> Add Query');
                $("#queryDisplay-modal .modal-footer").html('<button type="button" class="btn btn-sm btn-primary waves-effect waves-light submit-query"'
                		+ 'onclick="javascript:postQuery();">'
                		+ 'Submit'
                		+ '</button>');
                $("#queryDisplay-modal").modal('show');
        	}
        }
    });
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : 
**/
function viewQuery(id) {
    $(".lichfl-ajax-overlay").show();
	$('#queryTab li:first-child a').tab('show');
    $.ajax({
        url: "view",
        type: "GET",
        data: {
            'id': id
        },
       success: function(resp) {
    	   $(".lichfl-ajax-overlay").hide();
	       	if (checkValidResp(resp)) {
	     	   var query = resp.query;
	    	   loadQuery($('#view-queryInfo-form'), query);
	    	   loadQueryWFs($('#view-table-tbody'), resp.querywf);
	    	   
	    	   if (query.status == 'open' || query.status == 'pending') {
	    		   $('#view-queryTabContent .view-reopen-btn').addClass('d-none');
	    		   $('#view-queryTabContent .view-close-btn').removeClass('d-none');
	    		   $('#view-queryTabContent .view-close-btn').removeAttr('onclick');
	    		   $('#view-queryTabContent .view-close-btn').attr('onclick', 'closeQuery(\'' + query.id + '\')');
	    	   } else if (query.status == 'closed') {
	    		   $('#view-queryTabContent .view-reopen-btn').removeClass('d-none');
	    		   $('#view-queryTabContent .view-close-btn').addClass('d-none');
	    		   $('#view-queryTabContent .view-reopen-btn').removeAttr('onclick');
	    		   var stringifyResp = JSON.stringify(resp);
	    		   stringifyResp = stringifyResp.replace(/'/g, '\\\'');
	    		   stringifyResp = stringifyResp.replace(/"/g, '\\"');
	    		   $('#view-queryTabContent .view-reopen-btn').attr('onclick', 'reOpenQuery(\'' + stringifyResp + '\')');
	    	   } else {
	    		   $('#view-queryTabContent .view-buttons').hide();
	    	   }
	    	   
	           $('.add-query').addClass('d-none');
	           $('.view-query-info').removeClass('d-none');
	           $('#queryDisplay-modal .modal-title').html('<i class="fa fa-hashtag fa-fw"></i> View Query');
	           $("#queryDisplay-modal .modal-footer").html('<button type="button" class="btn btn-sm btn-primary waves-effect waves-light submit-query"'
	           		+ 'data-dismiss="modal">'
	           		+ 'Close'
	           		+ '</button>');
	           $('#queryDisplay-modal').modal('show');
	    	}
       }
    });
}

function loadQueryWFs(container, data) {
	container.html('');
	if (Array.isArray(data) && data.length > 0) {
		data.forEach(function(d){
			var row = $('<tr>')
			, cell = function(text) {
				var c = $('<td>');
				c.html(text);
				return c;
			};
			
			row.append(cell(d.updatedDate));
			row.append(cell(d.action));
			row.append(cell(d.actionBy));
			row.append(cell(d.comments));
			
			container.append(row);
		});
	} else {
		var row = $('<tr>')
			, cell = $('<td>', {'colspan':4});
		cell.text('No records available');
		row.append(cell);
		container.append(row);
	}
}

function loadQuery(container, data) {
	container.find('#empCode').val(data.empCode);
	container.find('#module').val(data.module);
	container.find('#submodule').val(data.subModule);
	container.find('#errorCode').val(data.errorCode);
	container.find('#type').val(data.type);
	container.find('#assignedTo').val(data.assignedTo);
	container.find('#typeInput').val(data.typeInput);
	container.find('#description').val(data.description);
	container.find('#category_desc').val(data.category_desc);
	if (data.lastResponse) {
		container.find('#lastResponse').val(data.lastResponse);
	}
	if (data.fileName) {
		container.find('#view-attach').attr('onclick', 'viewAttachment(\'' + data.fileName + '\')');
		container.find('#view-attach-cont').show();
	} else {
		container.find('#view-attach-cont').hide();
	}
}

function viewAttachment(name) {
	$('#modalYT').modal('show');
	$('#attach-iframe').attr('src', 'viewdoc?name=' + name);
	var extn = name.substr(name.lastIndexOf('.')+1);
	if (extn == 'xls' || extn == 'doc') {
		setTimeout(function() {
			$('#modalYT').modal('hide');
		}, 1500);
	}
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : 
**/
function reOpenQuery(data) {
	
    $('#queryReopenForm').find("input[type=text], textarea").val('');
    $('#queryReopenForm').find("select").val('');
    clearReopenFile();

	
	$('#queryReopenTab li:first-child a').tab('show');	
    $('#queryDisplay-modal').modal('hide');
    $('#queryReopen-modal').modal('show');
    data = data.replace(/"bold"+/g, 'bold');   
    var data = JSON.parse(data);
    $('#reopenId').val(data.query.id);
    var container = $('#queryInfo-form');
    container.find('#reopenempCode').val(data.query.empCode);
	container.find('#reopenModules').val(data.query.module);
	container.find('#reopenSubmodules').val(data.query.subModule);
	container.find('#reopenErrorcode').val(data.query.errorCode);
	container.find('#reopenType').val(data.query.type);	
	container.find('#reopenTypenumber').val(data.query.typeInput);
	container.find('#reopenDesc').val(data.query.description);
	container.find('#reopencategory_desc').val(data.query.category_desc);
	if (data.query.lastResponse) {
		container.find('#reopenClosing').val(data.query.lastResponse);
	}
	if (data.query.fileName) {
		container.find('#view-reopen-attch').attr('onclick', 'viewAttachment(\'' + data.query.fileName + '\')');
		container.find('.view-reopen-attch-cont').show();
	} else {
		container.find('.view-reopen-attch-cont').hide();
	}
	
	$('#reopenWorkflow-table tbody').html('');
	var workflowData = data.querywf;
	if (Array.isArray(workflowData) && workflowData.length > 0) {
		workflowData.forEach(function(d){
			var row = $('<tr>')
			, cell = function(text) {
				var c = $('<td>');
				c.html(text);
				return c;
			};
			
			row.append(cell(d.updatedDate));
			row.append(cell(d.action));
			row.append(cell(d.actionBy));
			row.append(cell(d.comments));
			
			$('#reopenWorkflow-table tbody').append(row);
		});
	} else {
		var row = $('<tr>')
			, cell = $('<td>', {'colspan':4});
		cell.text('No records available');
		row.append(cell);
		$('#reopenWorkflow-table tbody').append(row);
	}
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : 
**/
$('#add-modules').change(function(evnt) {
    $(".lichfl-ajax-overlay").show();
    var selectedValue = $(this).val();
    if (selectedValue == '-1') {
        return;
    }
    $.ajax({
        url: "submod/load",
        type: "GET",
        data: {
            'id': selectedValue
        },
        success: function(resp) {
        	$(".lichfl-ajax-overlay").hide();
        	if (checkValidResp(resp)) {
                populateOptions($('#add-submodules'), resp);
        	}
        }
    });
});
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : 
**/
function postQuery() {
    var data = new FormData(),
        fields = $('#add-query-form').serializeArray();
    jQuery.each(fields, function(i, field) {
        data.append(field.name, field.value);
    });
    var fileElements = $('#add-query-form [type="file"]');
    for (var i = 0; i < fileElements.length; i++) {
        data.append(fileElements[i].name, fileElements[i].files[0]);
    }

    var successFn = function(resp) {
    	$(".lichfl-ajax-overlay").hide();
    	if (checkValidResp(resp)) {
            $("#queryDisplay-modal").modal('hide');
            notification('Query Added SuccessFully');
            $('.open-query').addClass('active');
            $('.closed-query').removeClass('active');
            $('.query-panel .card-header').addClass('light-blue').removeClass('blue-grey');
            $('.query-panel .card-header .query-title').html('<i class="fa fa-folder-open fa-fw"></i> Open Queries');
            $('#refresh-button').removeAttr('onclick');
            $('#refresh-button').attr('onclick', 'javascript:loadOpenQueries();');
            $('.query-table-container').hide();
            $('.query-table-loader').show();
            table.clear().draw();
            displayRecords(resp, false);
            $('#add-query-form').bootstrapValidator('resetForm', true);
    	}
    }, errorFn = function(xhr) {
    	$(".lichfl-ajax-overlay").hide();
    	if (processErrorResponse(xhr.responseText)) {
        	$('#add-query-form').bootstrapValidator('resetForm', true);
    	}
	}
    $('#add-query-form').bootstrapValidator().off('success.form.bv').on('success.form.bv', function(e) {
    	$(".lichfl-ajax-overlay").show();
	    $.ajax({
	        url: 'add',
	        type: 'post',
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false,
	        cache: false,
	        data: data,
	        success: successFn,
	        error: errorFn
	    });
    });
    $('#add-query-form').bootstrapValidator('validate');
}

function processErrorResponse(data) {
	if (typeof data == 'string' && data.indexOf('redirectlogin') != -1) {
		var message = data.hasOwnProperty("message") ? data.message : 'Your session got expired. Please login again!';
		notification(message);
		location.href = "";
		return false;
	}
	notification('Please try again after sometime!');
	return true;
}

/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : Initialize the datatable
**/
var table = $('#query-table').DataTable({
    "scrollX": true,
    "scroller": {
        "displayBuffer": 10
    },
    "order": [
        [0, "desc"]
    ],
    "scrollCollapse": true,
    "paging": true,
    "pagingType": "full_numbers",
    responsive: false,
    "lengthMenu": [
        [10, 50, 100, -1],
        [10, 50, 100, "All"]
    ],
});
/*--------------------------------------------------------------------------------------------------------------------------------------*/

function submitReopen() {
    var data = new FormData(),
    fields = $('#queryReopenForm').serializeArray();
	jQuery.each(fields, function(i, field) {
	    data.append(field.name, field.value);
	});
	var fileElements = $('#queryReopenForm [type="file"]');
	for (var i = 0; i < fileElements.length; i++) {
	    data.append(fileElements[i].name, fileElements[i].files[0]);
	}
	
	var successFn = function(resp) {
		$(".lichfl-ajax-overlay").hide();
    	if (checkValidResp(resp)) {
    	    $("#queryReopen-modal").modal('hide');
    	    notification('Query Re-opened SuccessFully');
    	    $('.open-query').addClass('active');
    	    $('.closed-query').removeClass('active');
    	    $('.query-panel .card-header').addClass('light-blue').removeClass('blue-grey');
    	    $('.query-panel .card-header .query-title').html('<i class="fa fa-folder-open fa-fw"></i> Open Queries');
    	    $('#refresh-button').removeAttr('onclick');
    	    $('#refresh-button').attr('onclick', 'javascript:loadOpenQueries();');
    	    $('.query-table-container').hide();
    	    $('.query-table-loader').show();
    	    table.clear().draw();
    	    displayRecords(resp, false);
    	    $('#queryReopenForm').bootstrapValidator('resetForm', true);
    	}
	}, errorFn = function(xhr) {
		$(".lichfl-ajax-overlay").hide();
		if (processErrorResponse(xhr.responseText)){
			$('#queryReopenForm').bootstrapValidator('resetForm', true);
		}
	}
	$('#queryReopenForm').bootstrapValidator().off('success.form.bv').on('success.form.bv', function(e) {
		$(".lichfl-ajax-overlay").show();
	    $.ajax({
	        url: 'reopen',
	        type: 'post',
	        enctype: 'multipart/form-data',
	        processData: false,
	        contentType: false,
	        cache: false,
	        data: data,
	        success: successFn,
	        error: errorFn
	    });
	});
	$('#queryReopenForm').bootstrapValidator('validate');
}

/**
	Purpose : Reopen Query Modal- Add and remove modal-footer buttons based on tab selection
**/
$('#queryReopenReason-tab').click(function() {
    $('#queryReopen-modal .modal-footer').html('<button type="button" class="btn btn-sm btn-primary waves-effect waves-light" onclick="javascript:submitReopen();">'
    		+ 'Submit'
    		+ '</button>'
    		+ '<button type="button" class="btn btn-sm btn-primary waves-effect waves-light" data-dismiss="modal">'
    		+ 'Close'
    		+ '</button>');
});

$('#queryReopenWorkflow-tab , #queryReopenInfo-tab').click(function() {
    $('#queryReopen-modal .modal-footer').html('<button type="button" class="btn btn-sm btn-primary waves-effect waves-light" data-dismiss="modal">'
    		+ 'Close'
    		+ '</button>');
});
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : Add Query- Show notification
**/
function notification(msg) {
	$('.notification .msg').html(msg);
    $('.notification').addClass('show');    
    // After 3 seconds, remove the show class from DIV
    setTimeout(function() {
        $('.notification').removeClass('show');
    }, 3000);
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : Close the query and get user comments for closing query
**/
$("#modalCloseQueryComment").on("hide.bs.modal", function () {	
	$('#closeQueryForm').bootstrapValidator('resetForm', true);
});
function closeQuery(id){		
	$('#modalCloseQueryComment').modal('show');	
	$('#closeQueryForm').find('textarea').val('');
	$('#query-no-closingwin').val(id);
}

function getQueryClosingComment(){
	$('#closeQueryForm').bootstrapValidator().off('success.form.bv').on('success.form.bv', function(e) {
		$(".lichfl-ajax-overlay").show();
		var data = $('#closeQueryForm').serialize();
		var closingComment = $('#closingComments').val();
	    $.ajax({
	        url: "close",
	        type: "POST",
	        data: data,
	        success: function(resp) {
	        	$(".lichfl-ajax-overlay").hide();
	        	if (checkValidResp(resp)) {
		        	$('#closeQueryForm').bootstrapValidator('resetForm', true);
	        		$('#modalCloseQueryComment').modal('hide');
	        		$('#queryDisplay-modal').modal('hide');
		    		notification('Query has been closed successfully');
		    		
		            $('.open-query').addClass('active');
		            $('.closed-query').removeClass('active');
		            $('.query-panel .card-header').addClass('light-blue').removeClass('blue-grey');
		            $('.query-panel .card-header .query-title').html('<i class="fa fa-folder-open fa-fw"></i> Open Queries');
		            $('#refresh-button').removeAttr('onclick');
		            $('#refresh-button').attr('onclick', 'javascript:loadOpenQueries();');
		            $('.query-table-container').hide();
		            $('.query-table-loader').show();
		            table.clear().draw();
		            displayRecords(resp, false);
	        	}
	        }
	    });
    });
	$('#closeQueryForm').bootstrapValidator('validate');	
}
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/**
	Purpose : Form validations for Close query comments form
**/
	$('#closeQueryForm').bootstrapValidator({
		trigger:'blur',
		fields: {
			comment: {
                validators: {
                    notEmpty:{
                    	message: 'Comments are required for closing query.'
                    }
					
                }
            }           
        },
	});
/*--------------------------------------------------------------------------------------------------------------------------------------*/
/**
	Purpose : Form validations for Add query form
**/
	$('#add-type').change(function (){
		 $('#add-query-form').data('bootstrapValidator').revalidateField('typeInput');
	});

	$('#add-query-form').bootstrapValidator({
		trigger:'blur',
		fields: {
			typeInput: {
                validators: {
                	callback: {  
                		message: 'Invalid input',
    	                callback: function(value, validator,$field) {
    	                	var addTypeChk = $('#add-type option:selected').text();
    	                	if(addTypeChk == 'Loan Number'){
    	                		var achk = /^[0-9]*$/.test(value);
    	                		var alen = value.length;
    	                		if(achk == false){    	                			
    	                			return {
                                        valid: false,
                                        message: 'Loan Number should be numeric'
                                    };
    	                		}
    	                		if (alen < 11 || alen > 45) {
    	                			return {
                                        valid: false,
                                        message: 'Loan Number should be minimum 11 numbers'
                                    };
    	                		}
    	                	} 
    	                	else if(addTypeChk == 'Application Id') {
    	                		var bchk = /^[0-9]*$/.test(value);
    	                		var blen = value.length;
    	                		if(bchk == false){
    	                			return {
                                        valid: false,
                                        message: 'Application Id should be numeric'
                                    };
    	                		}
    	                		if(blen < 8 || blen > 10){
    	                			return {
                                        valid: false,
                                        message: 'Application Id in between 8 to 10 numbers only'
                                    };
    	                		}
    	                	}
    	                	else if(addTypeChk == 'Employee Id') {
    	                		var dchk = /^[0-9]*$/.test(value);
    	                		var dlen = value.length;
    	                		if(dchk == false){
    	                			return {
                                        valid: false,
                                        message: 'Employee Id should be numeric'
                                    };
    	                		}
    	                		if(dlen > 6){
    	                			return {
                                        valid: false,
                                        message: 'Employee Id should be maximum 6 numbers'
                                    };
    	                		}
    	                	}
    	                	else {
    	                		var cchk = /^([a-zA-Z0-9-/.:]+([a-zA-Z0-9-/.:]+)*)(\s([a-zA-Z0-9-/.:]+([a-zA-Z0-9-/.:]+)*))*$/.test(value);
    	                		var clen = value.length;
    	                		if(cchk == false || clen > 45){    	                			
    	                			return {
                                        valid: false,
                                        message: 'Field should be alphanumeric and - / . :, and less than 45 characters'
                                    };
    	                		}    	                		
    	                	}    	                	
    	                	return true;
    	                }
    	            },
                    notEmpty:{
                    	message: 'This is a required field'
                    }
					
                }
            },
            errorCode: {
                validators: {
                	stringLength: {
                        max: 200,
                        message: 'Please enter only upto 200 characters'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty:{
                    	message: 'This is a required field'
                    },
                    stringLength: {
                    	min: 6,
                        max: 1000,
                        message: 'Please enter minimum 6 and maximum 1000 characters.'
                    }					
                }
            },
			importFile: {
                validators: {                 	
                    file: {
                    	extension: 'jpeg,jpg,png,pdf,doc,docx,xls,xlsx',
                        type: 'image/jpeg,image/png,application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
                        maxSize: 2097152,   // 2048 * 1024
                        message: 'The selected file is not valid'
                    },
                }
            }          
        },
	});
/*--------------------------------------------------------------------------------------------------------------------------------------*/
	/**
	Purpose : Form validations for Reopen query form
**/
	$('#queryReopenForm').bootstrapValidator({
		trigger:'blur',
		fields: {			
            reopenerrorcode: {
                validators: {
                	stringLength: {
                        max: 200,
                        message: 'Please enter only upto 200 characters'
                    }
                }
            },
            reopendesc: {
                validators: {
                    notEmpty:{
                    	message: 'This is a required field'
                    },
                    stringLength: {
                    	min: 6,
                        max: 1000,
                        message: 'Please enter minimum 6 and maximum 1000 characters.'
                    }					
                }
            },
            reopenimportFile: {
                validators: {                 	
                    file: {
                    	extension: 'jpeg,jpg,png,pdf,doc,docx,xls,xlsx',
                        type: 'image/jpeg,image/png,application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
                        maxSize: 2097152,   // 2048 * 1024
                        message: 'The selected file is not valid'
                    },
                }
            }          
        },
	});
/*--------------------------------------------------------------------------------------------------------------------------------------*/

/* Search Page JS */

function loadSearchModal(){
	$("#loadSearchModal").modal('show');
}

function replyModal(){
	$("#replyModal").modal('show');
}

