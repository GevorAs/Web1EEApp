<%--
  Created by IntelliJ IDEA.
  User: Arianna
  Date: 20.01.2018
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
</head>
<body>
bari galust
<%
    if (request.getAttribute("errMessage") != null) {
%>
<span style="color: red"><%=
request.getAttribute("errMessage")
%></span>
<% }
%>
<form action="/login" method="post">
    Email <input type="email" name="email"><br>
    Password <input type="password" name="password"><br>
    <input type="submit" value="login">
</form>
<br>
<a href="register.jsp"><input type="button" value="register"> </a>
</body>
</html>

