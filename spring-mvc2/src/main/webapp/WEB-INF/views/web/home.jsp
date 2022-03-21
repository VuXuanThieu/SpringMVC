<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Trang chủ</title>
	</head>
<body>
	<div class="main-content fix">
		<form action="<c:url value='/trang-chu'/>" id="formSubmit" method="get">
			
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<form action="<c:url value='/trang-chu'/>">
					 	   		Tìm kiếm tên sách: <input type="text" name="keyword" id="keyword" size="50" value="${keyword}" required />
						   		&nbsp;
					 	   		<input type="submit" value="Search" />
							</form>	
						</ul>
						<!-- /.breadcrumb -->
					</div>
					
					
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<c:if test="${not empty message}">
									<div class="alert alert-${alert}">
			  							${message}
									</div>
								</c:if>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>Tên sách</th>
														<th>Bìa sách</th>
														<th>Mô tả ngắn</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td>${item.title}</td>
															<td><img src="${item.thumbnail}" height="300" style="display: block; margin: 0 auto"></td>
															<td>${item.shortDescription}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<ul class="pagination" id="pagination"></ul>	
											<input type="hidden" value="" id="page" name="page"/>
											<input type="hidden" value="" id="limit" name="limit"/>									
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</form>
		</div>
		<!-- /.main-content -->
			
			<script>
			var totalPages = ${model.totalPage};
			var currentPage = ${model.page};
			
			
			$(function () {
		        window.pagObj = $('#pagination').twbsPagination({
		            totalPages: totalPages,
		            visiblePages: 10,
		            startPage: currentPage,
		            onPageClick: function (event, page) {
		            	if (currentPage != page) {
		            		$('#limit').val(10);
							$('#page').val(page);
							$('#formSubmit').submit();
						}
		            }
		        });
		    });
		</script>
	
</body>
</html>