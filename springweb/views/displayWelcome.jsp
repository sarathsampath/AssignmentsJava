<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="logout.jsp" %>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<h4>Welcome....:${formBeanObject.getName()}</h4>
<h1>UserName:....:<%=session.getAttribute("Username") %></h1>
<h4>CustomerName:....:<%=session.getAttribute("CustomerName") %></h4>
<body>
	<spring:form method="post" action="processItems">
	<input type="submit" value="Next..">
		
	</spring:form>
</body>
</html>