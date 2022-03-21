<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="publisherURL" value="/quan-tri/nha-xuat-ban/danh-sach" />
<c:url var="editPublisherURL" value="/quan-tri/nha-xuat-ban/chinh-sua" />
<c:url var="publisherAPI" value="/api/publisher" />
<html>
<head>
<title>Chỉnh sửa nhà xuất bản</title>
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
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Mã nhà xuất bản</label>
								<div class="col-sm-9">
									<form:input path="code" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên nhà xuất bản</label>
								<div class="col-sm-9">
									<form:input path="name" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Địa chỉ xuất bản</label>
								<div class="col-sm-9">
									<form:input path="address" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Email xuất bản</label>
								<div class="col-sm-9">
									<form:input path="email" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							<form:hidden path="id" id="publisherId" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdatePublisher">
											<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật nhà xuất bản
										</button>
									</c:if>
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdatePublisher">
											<i class="ace-icon fa fa-check bigger-110"></i> Thêm nhà xuất bản
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
	$('#btnAddOrUpdatePublisher').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
	    $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
	    var id = $('#publisherId').val();
	    if (id == "") {
	    	addPublisher(data);
	    } else {
	    	updatePublisher(data);
	    }
	});
	
	function addPublisher(data) {
		$.ajax({
            url: '${publisherAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editPublisherURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${publisherURL}?page=1&limit=10&message=error_system";
            }
        });
	}
	
	function updatePublisher(data) {
		$.ajax({
            url: '${publisherAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editPublisherURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${editPublisherURL}?id="+result.id+"&message=error_system";
            }
        });
	}
</script>

</body>
</html>
