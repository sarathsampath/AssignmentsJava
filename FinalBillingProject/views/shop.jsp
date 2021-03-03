<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.ArraytemDTO" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shop</title>
<%@ include file="style.jsp" %>
<style>
h1{
	text-align: center;
	letter-spacing: 6px;
	font-size: 3em !important;
}
.col-md-4{
	padding:10px;
}

</style>
</head>
<body>

<nav class="navbar bg-primary navbar-expand-sm justify-content-end">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link text-light" href="#"><%=session.getAttribute("CustomerName") %></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"><%@ include file="logout.jsp" %></a>
    </li>
  </ul>
</nav>
<%
ArraytemDTO obj=(ArraytemDTO)session.getAttribute("Items");
String []Image=obj.getItemImage();
String []Description=obj.getItemDescription();
int []Price=obj.getPrice();
int []itemid=obj.getItemId();
%>

<h1>RESTAURANT</h1>

<div class="container">
<spring:form action="itemsubmit" method="post">
	<div class="row">
		<div class="col-md-4 card">
		<input type="hidden" name="formid" value="shopping">
		<input type="hidden" name="shopid" value="shop1">
		<input type="checkbox" class="form-control" name=<%=itemid[0]%> value= <%=Description[0]%> ><b><%=Description[0]%> </b>
		<img src=<%=Image[0]%> width="100px" height="150px" style="margin-left:130px;">
		<div style="display: flex;margin-top:50px;">
		<p>Price:<%=Price[0]%>   </p>
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity: <input class="form-control" type="number" name=<%=Description[0]%>>
		</div>
		
		
		
		</div>
	<div class="col-md-4 card">
	<input type="checkbox" class="form-control" name=<%=itemid[1]%> value=<%=Description[1]%> > 
	<b><%=Description[1]%> </b>
	<img src=<%=Image[1]%> width="130px" height="150px" style="margin-left:110px;">
	<div style="display: flex;margin-top:50px;">
		<p>Price:<%=Price[1]%></p>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity:<input type="number" class="form-control" name=<%=Description[1]%>>
		</div>
		</div>
	<div class="col-md-4 card">
	
		<input type="checkbox" class="form-control" name=<%=itemid[2]%> value=<%=Description[2]%> > 
		<b><%=Description[2]%> </b>
		<img src=<%=Image[2]%> width="60px" height="150px" style="margin-left:140px;">
		<div style="display: flex;margin-top:50px;">	
		<p>Price:<%=Price[2]%>   </p>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity:<input type="number" class="form-control" name=<%=Description[2]%>>
		</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 card">
		<input type="checkbox" class="form-control" name=<%=itemid[3]%> value=<%=Description[3]%> > 
		<b><%=Description[3]%> </b>
		<img src=<%=Image[3]%> width="110px" height="150px" style="margin-left:120px;" >
		<div style="display: flex;margin-top:50px;">
		<p>Price:<%=Price[3]%>   </p>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity:<input type="number" class="form-control" name=<%=Description[3]%>>
		</div>
		</div>
	<div class="col-md-4 card">
	
		<input type="checkbox" class="form-control" name=<%=itemid[4]%> value=<%=Description[4]%> > 
		<b><%=Description[4]%> </b>
		<img src=<%=Image[4]%> width="160px" height="150px" style="margin-left:100px;" >
		<div style="display: flex;margin-top:50px;">
		<p>Price:<%=Price[4]%>   </p>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity:<input type="number" class="form-control" name=<%=Description[4]%>>
		</div>
		</div>
	<div class="col-md-4 card">
	
		<input type="checkbox" class="form-control" name=<%=itemid[5]%> value=<%=Description[5]%> > 
		<b><%=Description[5]%> </b>
		<img src=<%=Image[5]%> width="130px" height="140px" style="margin-left:110px;">
		<div style="display: flex;margin-top:60px;">
		<p>Price:<%=Price[5]%>   </p>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity:<input type="number" class="form-control" name=<%=Description[5]%>>
		</div>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-5">
		</div>
		<div class="col-md-2">
		<input class="btn btn-success" type="submit" value="Generate Bill">
		</div>
	</div>
	
</spring:form>
</div>
	
		
		
		
		
		
	
	
</body>
</html>