<%@ page import="authorBook.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="authorBook.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Arianna
  Date: 20.01.2018
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%User user = (User) session.getAttribute("user");%>
helo <%=user.getName()%>&nbsp;<%=user.getSurname()%>
<div>
    <h2>Add Book</h2>
    <%
        if (request.getAttribute("errMessage") != null) {
    %>
    <span style="color: red"><%=request.getAttribute("errMessage")%></span>
    <%
        }
    %>
    <%
        if (request.getAttribute("info") != null) {
    %>
    <span style="color: red"><%=request.getAttribute("info")%></span>
    <%
        }
    %>

    <form action="/addBook" method="post">

        Name:<br> <input type="text" name="name"><br>
        Author:<br> <input type="text" name="author"><br>
        Price:<br> <input type="number" name="price"><br>
        Description:<br> <input type="text" name="description"><br>
        <input type="submit">
    </form>
</div>
<div>
    <table border="1">
        <tr>
            <th>Book Name</th>
            <th>Author Name</th>
            <th>Book price</th>
            <th>Description</th>
        </tr>
        <% if (request.getAttribute("userBooks") != null) {
            List<Book> userBooks = (List) request.getAttribute("userBooks");
            for (Book userBook : userBooks) {%>
        <tr>
            <td><%=userBook.getName()%>
            </td>
            <td><%=userBook.getAuthor()%>
            </td>
            <td><%=userBook.getPrice()%>
            </td>
            <td><%=userBook.getDescription()%>
            </td>

        </tr>
        <% }
        }%>
    </table>
</div>
<style>

    .message {
        margin: 15px auto;
        background-color: red;
        width: 800px;
        height: 600px;
        padding: 10px;

    }

    .messageIn {
        overflow: scroll;
        height: 500px;
    }

    .container {
        border: 2px solid #dedede;
        background-color: #f1f1f1;
        border-radius: 5px;
        padding: 10px;
        margin: 10px 0;
    }

    .darker {
        border-color: #ccc;
        background-color: #ddd;
    }

    .container::after {
        content: "";
        clear: both;
        display: table;
    }

    .container img {
        float: left;
        max-width: 60px;
        max-height: 60px;
        width: 100%;
        margin-right: 20px;
        border-radius: 50%;
    }

    .container img.right {
        float: right;
        margin-left: 20px;
        margin-right: 0;
    }

    .time-right {
        float: right;
        color: #aaa;
    }

    .time-left {
        float: left;
        color: #999;
    }
</style>
<div class="message">
    <h2>Chat Messages</h2>
    <div class="messageIn">

        <%

            if (request.getAttribute("userBooks") != null) {
                List<Book> userBooks = (List) request.getAttribute("userBooks");
                int count = 2;
                for (Book userBook : userBooks) {

                    if (count % 2 == 0) {

        %>
        <div class="container">
            <img src="" alt="Avatar" style="width:100%;">
            <p><%=userBook.getName()%>&nbsp;
                <%=userBook.getAuthor()%>
                &nbsp;<%=userBook.getPrice()%>
                &nbsp;<%=userBook.getDescription()%>
            </p>
            <span class="time-left">11:00<%=count%></span>
        </div>
        <% count++;
        } else {

        %>
        <div class="container darker">
            <img src="" alt="Avatar" class="right" style="width:100%;">
            <p><%=userBook.getName()%>&nbsp;
                <%=userBook.getAuthor()%>
                &nbsp;<%=userBook.getPrice()%>
                &nbsp;<%=userBook.getDescription()%>
            </p>
            <span class="time-right">11:00<%=count%></span>
        </div>
        <% count++;
        }
        }
        }%>

        <%--<div class="container">--%>
            <%--<img src="/w3images/bandmember.jpg" alt="Avatar" style="width:100%;">--%>
            <%--<p>Hello. How are you today?</p>--%>
            <%--<span class="time-right">11:00</span>--%>
        <%--</div>--%>

        <%--<div class="container darker">--%>
            <%--<img src="/w3images/avatar_g2.jpg" alt="Avatar" class="right" style="width:100%;">--%>
            <%--<p>Hey! I'm fine. Thanks for asking!</p>--%>
            <%--<span class="time-left">11:01</span>--%>
        <%--</div>--%>

        <%--<div class="container">--%>
            <%--<img src="/w3images/bandmember.jpg" alt="Avatar" style="width:100%;">--%>
            <%--<p>Sweet! So, what do you wanna do today?</p>--%>
            <%--<span class="time-right">11:02</span>--%>
        <%--</div>--%>

        <%--<div class="container darker">--%>
            <%--<img src="/w3images/avatar_g2.jpg" class="right" alt="Avatar" style="width:100%;">--%>
            <%--<p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>--%>
            <%--<span class="time-left">11:05</span>--%>
        <%--</div>--%>
    </div>
</div>
</body>
</html>
