<%@ page language="java" contentType="text/html; charset=UTF-8"
	buffer="10kb" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Employees</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Employees Tables</div>
			<!-- /.panel-heading -->

			<div class="panel-body">
				<div class="row">
					<form id="searchForm" action="/employees/list" method="get">
						<div class="col-xs-2">
							<select class="form-control" name="type">
								<option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}" /> >--</option>
								<option value="E" <c:out value="${pageMaker.cri.type eq 'E'?'selected':''}" /> >사번</option>
								<option value="N" <c:out value="${pageMaker.cri.type eq 'N'?'selected':''}" />>이름</option>
							</select>
						</div>
						<div class="col-xs-2" style="padding: 0px;">
							<input class="form-control" type="text" name="keyword" value="<c:out value='${pageMaker.cri.keyword}' />"  />
						</div>
						<div class="col-xs-2">
							<button class="btn btn-default">Search</button>
						</div>
						<input type="hidden" name="pageNum"
							value="<c:out value="${pageMaker.cri.pageNum}" />" />  <input
							type="hidden" name="amount"
							value="<c:out value="${pageMaker.cri.amount}" />" />
					</form>
				</div>

				<table width="100%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>#사번</th>
							<th>이름</th>
							<th>성별</th>
							<th>생년월일</th>
							<th>입사일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty list}">
								<tr class="gradeU">
									<td>-</td> 
									
									<td>-</td>
									<td>-</td>
									<td class="center">-</td>
									<td class="center">-</td>
								</tr>
							</c:when>
							<c:when test="${!empty list}">
								<c:forEach items="${list}" var="emp">
									<tr class="gradeU">
										<td><c:out value="${emp.emp_no}" /></td>
										<td><c:out value="${emp.first_name} ${emp.last_name}" /></td>
										<td><c:if test="${emp.gender eq 'M'}">
												남성
											</c:if> <c:if test="${emp.gender eq 'F'}">
												여성
											</c:if></td>
										<td class="center"><fmt:formatDate pattern="yyyy-MM-dd"
												value="${emp.birth_date}" /></td>
										<td class="center"><fmt:formatDate pattern="yyyy-MM-dd"
												value="${emp.hire_date}" /></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>


					</tbody>
				</table>
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage-1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li
								class="paginate_button  ${(pageMaker.cri.pageNum == num)?'active':''}">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage+1}">Next</a></li>
						</c:if>
					</ul>
				</div>

				<form id="actionForm" action="/employees/list" method="get">
					<input type="hidden" name="pageNum"
						value="<c:out value='${pageMaker.cri.pageNum}'/>" /> <input
						type="hidden" name="amount"
						value="<c:out value='${pageMaker.cri.amount}' />" />
					
					<input type="hidden" name="type" value="<c:out value="${pageMaker.cri.type}" />" />
					<input type="hidden" name="keyword" value="<c:out value="${pageMaker.cri.keyword}" />" />
				</form>

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script type="text/javascript">
	$(document).ready(function() {
		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click", function(e) {
			e.preventDefault();
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		var searchForm = $('#searchForm');
		$('#searchForm button').on("click", function(e) {
			if (!searchForm.find("option:selected").val()) {
				alert("검색 종류!");
				return false;
			}
			if (!searchForm.find("input[name='keyword']").val()) {
				alert("키워드!");
				return false;
			}
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			searchForm.submit();
		});

	});
</script>

<%@ include file="../includes/footer.jsp"%>