<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
</head>
<body>
<nav class="navbar bg-primary navbar-expand-sm justify-content-end">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link text-light" href="#"><%@ include file="CustomerBack.jsp" %></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"><%@ include file="logout.jsp" %></a>
    </li>
  </ul>
</nav>
<div class="jumbotron text-center ">
<h1 class="text-success">Order Placed Successfully</h1>
<p>Customer Name : <%=session.getAttribute("CustomerName") %></p>
<p>Customer Mobile : <%=session.getAttribute("CustomerMobile") %></p>
<p>TotalAmount : <%=session.getAttribute("TotalAmount") %></p>
<h5>Thank You</h5>
</div>
<div style=text-align:center>
<%@ include file="pdfexcel.jsp" %>
</div>
</body>
</html>