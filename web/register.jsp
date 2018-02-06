<%--
  Created by IntelliJ IDEA.
  User: Arianna
  Date: 20.01.2018
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Accoun</title>
</head>
<body>
<a href="index.jsp">
    <button>Login</button>
</a><br>
<%
    if (request.getAttribute("errMessage") != null) {
%>
<span style="color: red"><%=
request.getAttribute("errMessage")
%></span>
<% }
%>
<form action="/register" method="post">
    Name: <input type="text" name="name"><br>
    Surname: <input type="text" name="surname"><br>
    Email: <input type="email" name="email"><br>
    Password: <input type="password" name="password"><br>
    re-password: <input type="password" name="repassword"><br>
    Male: <input type="radio" name="gender" checked required value="MALE">
    Female: <input type="radio" name="gender" value="FEMALE"><br>
    <input type="submit"><br>
</form>

</body>
</html>