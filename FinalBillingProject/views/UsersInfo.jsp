<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="style.jsp" %>
<title>Info</title>
</head>
<body>

<nav class="navbar bg-primary navbar-expand-sm justify-content-end">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#"><%@ include file="CustomerBack.jsp" %></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"><%@ include file="logout.jsp" %></a>
    </li>
  </ul>
</nav>

<div class="container">
	<div style="text-align:center;margin-top:100px">
		<h1>User Information</h1>
		<hr>
		<p>Employee Name : <b><%=session.getAttribute("Username") %></b> </p>
		<p>and</p>
		<p>Customer Name : <b> <%=session.getAttribute("CustomerName") %></b></p>
	</div>
	<div class="row" style="margin-top: 100px;">
	<div class="col-md-5"></div>
	<div class="col-md-2" style="padding: 20px;">

			
			<spring:form method="post" action="processItems">
			<button type="submit" class="btn btn-success btn-lg" style="width: 100%">NEXT<span class="glyphicon glyphicon-chevron-right"></span></button>
				
			</spring:form>
	</div>
	<div class="col-md-5"></div>
	</div>
</div>
</body>
</html>