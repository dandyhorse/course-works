<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.04.2016
  Time: 1:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<aside>

    <a href="add.jsp">Добавить</a>
    </br></br>
    <a href="index.jsp">Назад</a>
</aside>

<h1>Добавление</h1>

<form action="${pageContext.request.contextPath}/add" method="post">

    <input type="text" name="" title="login">
    <input type="password" name="" title="password">
    <input type="submit">

</form>

</body>
</html>
