<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Tags</title>
</head>
<body>
  <%!int coef = 3;%>
  <h1>Hello World!</h1>
  <%
  int i = 9;
  out.println(7 + 5);
  %>
  <br> Result:
  <%
  out.println(coef);
  %>
  <br> Result:
  <%=coef%>
</body>
</html>