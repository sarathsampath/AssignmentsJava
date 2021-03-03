<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<%@ include file="style.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
h1 {
	text-align:center;
	margin-top:100px;
	font-family: Arial, Helvetica, sans-serif;
}
</style>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<h1>Hi <%=session.getAttribute("Username") %>! Welcome</h1>
<p align="center">Please enter your customer name and mobile number to continue</p>
<body>
<div class="container" style="margin-top: 130px;">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4" >
		<spring:form commandName="CustomerBean" style="padding: 30px;"  class="form-control" method="post" action="customersubmit">
		
			Customer Name:<spring:input class="form-control" path="CustomerName"/><br>
			Customer Mobile:<spring:input class="form-control" path="CustomerMobile"/>
			<br>
			<input type="submit" class="btn btn-primary" style="width: 100%" value="Login">
			
		</spring:form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
</body>
</html>