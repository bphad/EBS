<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="row text-center pad-top">
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
			<div class="div-square">
				<a href="${pageContext.request.contextPath }/project"> 
					<i class="fa fa-circle-o-notch fa-3x"></i>
					<h4>Projects</h4>
				</a>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
			<div class="div-square">
				<a href="${pageContext.request.contextPath }/employee"> 
					<i class="fa fa-users fa-3x"></i>
					<h4>Employees</h4>
				</a>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
			<div class="div-square">
				<a href="${pageContext.request.contextPath }/salary"> 
					<i class="fa fa-clipboard fa-3x"></i>
					<h4>Salary Record</h4>
				</a>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
			<div class="div-square">
				<a href="${pageContext.request.contextPath }/search"> 
					<i class="fa fa-gear fa-3x"></i>
					<h4>Search</h4>
				</a>
			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
			<div class="div-square">
				<a href="blank.html"> <i class="fa fa-key fa-3x"></i>
					<h4>Admin</h4>
				</a>
			</div>
		</div>
	</div>
	
</body>
</html>