<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="bookURL" value="/quan-tri/sach/danh-sach" />
<c:url var="editBookURL" value="/quan-tri/sach/chinh-sua" />
<c:url var="bookAPI" value="/api/book" />
<html>
<head>
<title>Chỉnh sửa sách</title>
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
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên sách</label>
								<div class="col-sm-9">
									<form:input path="title" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Trang bìa</label>
								<div class="col-sm-9">
									<!-- <input type="file" class="col-xs-10 col-sm-5" id="thumbnail" name="thumbnail"/> -->
									<form:input path="thumbnail" cssClass="col-xs-10 col-sm-5" />
									<br></br> <img src="${model.thumbnail}" height="300">
								</div>
							</div>
							<div class="form-group">
								<label for="categoryCode"
									class="col-sm-3 control-label no-padding-right">Thể loại:</label>
								<div class="col-sm-9">
									<form:select path="categoryCode" id="categoryCode">
										<form:option value="" label="-- Chọn thể loại --" />
										<form:options items="${categories}" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label for="authorCode"
									class="col-sm-3 control-label no-padding-right">Tác giả:</label>
								<div class="col-sm-9">
									<form:select path="authorCode" id="authorCode">
										<form:option value="" label="-- Chọn tác giả --" />
										<form:options items="${authors}" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label for="publishserCode" class="col-sm-3 control-label no-padding-right">Nhà xuất bản:</label>
								<div class="col-sm-9">
									<form:select path="publisherCode" id="publisherCode">
										<form:option value="" label="-- Chọn nhà xuất bản --" />
										<form:options items="${publishers}" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Năm</label>
								<div class="col-sm-9">
									<form:input path="year" cssClass="col-xs-10 col-sm-5" />
								</div>
							</div>
							<div class="form-group">
								<label for="shortDescription" class="col-sm-3 control-label no-padding-right">Mô tả ngắn:</label>
								<div class="col-sm-9">
									<form:textarea path="shortDescription" rows="5" cols="10"
										cssClass="form-control" id="shortDescription" />
								</div>
							</div>
							<div class="form-group">
								<label for="content"
									class="col-sm-3 control-label no-padding-right">Nội dung:</label>
								<div class="col-sm-9">
									<form:textarea path="content" rows="5" cols="10"
										cssClass="form-control" id="content" />
								</div>
							</div>
							<form:hidden path="id" id="bookId" />
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<c:if test="${not empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateBook">
											<i class="ace-icon fa fa-check bigger-110"></i> Cập nhật bài viết
										</button>
									</c:if>
									<c:if test="${empty model.id}">
										<button class="btn btn-info" type="button"
											id="btnAddOrUpdateBook">
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
	$('#btnAddOrUpdateBook').click(function (e) {
	    e.preventDefault();
	    var data = {};
	    var formData = $('#formSubmit').serializeArray();
	    $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
	    var id = $('#bookId').val();
	    if (id == "") {
	    	addBook(data);
	    } else {
	    	updateBook(data);
	    }
	});
	
	function addBook(data) {
		$.ajax({
            url: '${bookAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editBookURL}?id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${bookURL}?page=1&limit=10&message=error_system";
            }
        });
	}
	
	function updateBook(data) {
		$.ajax({
            url: '${bookAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${editBookURL}?id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${editBookURL}?id="+result.id+"&message=error_system";
            }
        });
	}
</script>

</body>
</html>
