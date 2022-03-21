<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="borrowURL" value="/quan-tri/muon/danh-sach" />
<c:url var="editBorrowURL" value="/quan-tri/muon/chinh-sua" />
<c:url var="borrowAPI" value="/api/borrow" />
<html>
<head>
<title>Chỉnh sửa mượn</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>

					<li><a href="#">Forms</a></li>
					<li class="active">Form Elements</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty message}">
							<div class="alert alert-${alert}">${message}</div>
						</c:if>
						
						<form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
							
 							<div class="form-group">
								<label for="userId" class="col-sm-3 control-label no-padding-right">Tên người mượn sách:</label>
								<div class="col-sm-9">
									<form:select path="userId" id="userId">
										<form:option value="" label="-- Chọn người mượn --" />
										<form:options items="${users}" />
									</form:select>
								</div>
							</div>
							
							<div class="form-group">
								<label for="bookId" class="col-sm-3 control-label no-padding-right">Tiêu đề sách:</label>
								<div class="col-sm-9">
									<form:select path="bookId" id="bookId">
										<form:option value="" label="-- Chọn tiêu đề sách --" />
										<form:options items="${books}" />
									</form:select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Ngày trả</label>
								<div class="col-sm-9">
									<%-- <form:input path="returnDate" cssClass="col-xs-10 col-sm-5" /> --%>
									<form:input type="date" path="returnDate" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							<form:hidden path="id" id="borrowId" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateBorrow">
											<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật bài viết
										</button>
									</c:if>
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateBorrow">
											<i class="ace-icon fa fa-check bigger-110"></i> Thêm bài viết
										</button>
									</c:if>

									&nbsp; &nbsp; &nbsp;
									<button class="btn" type="reset">
										<i class="ace-icon fa fa-undo bigger-110"></i> Hủy
									</button>
								</div>
							</div>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

<script>
	$('#btnAddOrUpdateBorrow').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
	    $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
	    var id = $('#borrowId').val();
	    if (id == "") {
	    	addBorrow(data);
	    } else {
	    	updateBorrow(data);
	    }
	});
	
	function addBorrow(data) {
		$.ajax({
            url: '${borrowAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editBorrowURL}?id="+result.id+"&message=insert_success";
            	/* window.location.href = "/spring-mvc/quan-tri/muon/danh-sach?page=1&limit=10"; */
            },
            error: function (error) {
            	window.location.href = "${borrowURL}?page=1&limit=10&message=error_system";
            }
        });
	}
	
	function updateBorrow(data) {
		$.ajax({
            url: '${borrowAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editBorrowURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${editBorrowURL}?id="+result.id+"&message=error_system";
            }
        });
	}
</script>

</body>
</html>
