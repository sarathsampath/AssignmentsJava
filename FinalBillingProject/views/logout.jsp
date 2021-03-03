<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
<%@ include file="style.jsp" %>
</head>
<body>
	<spring:form action="processlogout" method="post">
		<input type="submit" class="btn btn-danger" value="Logout">
	</spring:form>
</body>
</html>