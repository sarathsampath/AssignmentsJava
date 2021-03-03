	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="style.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="style.jsp" %>
<meta charset="ISO-8859-1">
<title>Login-RESTRAUNT</title>
<style>
 
    .error {
        color: #ff0000;
    }
    body{
		background-image: url('https://wallpaperaccess.com/full/462860.jpg');
		background-size:100%;
    }
    .card {
background-color: #ffffffb5;
}
    h3 {
    text-align:center;
    font-weight:bold;
    font-family: monospace;
    font-size: 40px;
    padding:20px;
    }
    small {
    text-align:center;
    margin-top: -20px;
    }
</style>

</head>
<body>

<div class="container">
	<div class="row" style="margin-top: 200px;">
	<div class="col-md-4"></div>
	<div class="col-md-5 card" style="padding: 40px;">
		<h3 class="text-danger">Java Restaurant</h3>
		<small>Food<i>crave</i></small>
		<br>
		<spring:form commandName="formBeanObject" autocomplete="off" method="post" action="loginsubmit">
		
		Username<spring:input class="form-control" path="Name"/>
		<spring:errors path="Name"  cssClass="error"/>
		<br>
		Password<spring:input type="password" class="form-control" path="Password"/>
		<spring:errors path="Password" cssClass="error"/>
		<br>
		<input  style="width:100%" class="btn btn-danger"  type="submit" value="Login">
		
	</spring:form>
	</div>
	</div>
</div>

	
</body>
</html>