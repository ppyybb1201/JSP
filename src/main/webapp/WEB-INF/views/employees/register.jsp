<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<div class="panel panel-primary">
			<div class="panel-heading">
				employees Register Page
				<!-- <button id="regBtn" type="button" class="btn btn-xs pull-right">Register
					New Board</button> -->
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form id="regForm" method="post" action="/employees/register">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-4">
								<div class="form-group">
									<label>First Name</label> <input class="form-control"
										name="first_name" placeholder="First Name">
								</div>
							</div>
							<div class="col-xs-3">
								<div class="form-group">
									<label>Last Name</label> <input class="form-control"
										name="last_name" placeholder="Last Name">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label>Birth Date</label> <input class="form-control" type="date"
								name="birth_date" placeholder="yyyy-MM-dd">
						</div>

						<div class="form-group">
							<label>Gender</label>
							<div class="radio-inline">
								<label> <input type="radio" name="gender"
									id="optionsRadios1" value="M" checked="">Male
								</label>
							</div>
							<div class="radio-inline">
								<label> <input type="radio" name="gender"
									id="optionsRadios2" value="F">Female
								</label>
							</div>

						</div>
						<div class="form-group">
							<label>Hire Date</label> <input class="form-control" type="date"
								name="hire_date" placeholder="yyyy-MM-dd">
						</div>

						<div class="form-group">
							<div class="row">
								<div class="col-xs-3">
									<label>Department</label> <select class="form-control"
										name="dept_no" id="sel_department">
										<option value="">--</option>
									</select>
								</div>
								<div class="col-xs-3">
									<label>Title</label> <select class="form-control" name="title"
										id="sel_title">
										<option value="">--</option>
									</select>
								</div>
							</div>
						</div>

						<button type="submit" class="btn btn-primary">Register</button>
						<button type="reset" class="btn btn-danger">Reset</button>
					</div>
				</form>

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<script type="text/javascript" src="/resources/js/department.js"></script>
<script>
	$(document).ready(function() {
		var selDept = $('#sel_department');
		var selDeptTitle = $('#sel_title');
		
		deptService.getDeptList(function(list){
			showDeptList(list);
		});
		

	
function showDeptList(list){
	var str = "<option value=''>--</option>";
	list.forEach(d => {
		str += "<option value='"+d.dept_no+"'>"+d.dept_name+"</option>";
	});
	selDept.html(str);
}

function showDeptTitleList(list){
	var str = "<option value=''>--</option>";
	list.forEach(d => {
		str += "<option value='"+d.title+"'>"+d.title+"</option>";
	});
	selDeptTitle.html(str);
}

selDept.on("change",function(e){
	var dept_no = $(this).val();
	if(dept_no == "" || dept_no == null) return;
	deptService.getDeptTitleList(dept_no, function(list){
		showDeptTitleList(list);
	});
});


});
	

</script>
<!--  -->
<%@include file="../includes/footer.jsp"%>
