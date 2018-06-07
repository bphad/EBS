<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.text.DateFormat" import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Manager To Project</title>
</head>
<body>

	<div id="wrapper">
		<c:import url="header.jsp" />
		<c:import url="sidebar.jsp" />

		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-lg-12">
						<h2>EBS System - User Dashboard</h2>
					</div>
				</div>
				<!-- /. ROW  -->
				<hr />
				<div class="row">
					<div class="col-lg-12 ">
						<div class="alert alert-info">
							<strong>Welcome User ! </strong> You Have No pending New Messages.
						</div>
					</div>
				</div>
				<!-- /. ROW  -->
				<c:import url="main.jsp" /><br>
				<c:if test="${param.msg != null }">
					<div class="alert alert-warning">
						<c:out value="${param.msg }" />
					</div>
				</c:if>
				<h4>Assign Manager to Project</h4>
				<form method="post" action="${pageContext.request.contextPath }/AssignManagerToProject">
					<p>
						Select Manager :<select name="employee" class="form-control">
							<c:forEach var="e" items="${emp_list }">
								<option value='<c:out value="${p.getId() }"/>'>
									<c:out value="${e.getName() }" /> --
									<c:out value="${e.getJob_title() }" />
								</option>
							</c:forEach>
						</select> 
						Select Project :<select name="project" class="form-control">
							<c:forEach var="p" items="${proj_list }">
								<option value='<c:out value="${p.getId() }"/>'>
									<c:out value="${p.getName() }" /> --
									<c:out value="${p.getClient_name() }" />
								</option>
							</c:forEach>
						</select><BR> 
						<input type="submit" value="Assign to project" class="btn btn-primary">
					</p>
				</form>
			</div>
		</div>
	</div>

</body>
</html>