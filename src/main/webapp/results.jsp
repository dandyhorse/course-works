<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<aside>

    <a href="<c:url value="/add"/>">Добавить</a>
    <br><br>
    <a href="index.jsp">Назад</a>

</aside>

<div class="tables">
    <h1>Таблица Пользователей</h1>
    <table>
        <tr>
            <td>Login</td>
            <td>Password</td>
            <td>Действия</td>
        </tr>
        <%--@elvariable id="users" type="java.util.List<User>"--%>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr>
                <td>${user.login}</td>
                <td> ${user.password}</td>
                <td>
                    <a href="<c:url value="/edit?login=${user.login}"/>">Edit</a>
                    <a href="<c:url value="/delete?login=${user.login}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h1>Таблица Администраторов</h1>
    <table>
        <tr>
            <td>Login</td>
            <td>Password</td>
            <td>Действия</td>
        </tr>

        <%--@elvariable id="admins" type="java.util.List<Admin>"--%>
        <c:forEach items="${admins}" var="admin" varStatus="status">
            <tr>
                <td>${admin.login}</td>
                <td> ${admin.password}</td>
                <td>
                    <a href="<c:url value="/edit?login=${admin.login}"/>">Edit</a>
                    <a href="<c:url value="/delete?login=${admin.login}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<h1>Колонка Новостей</h1>
<%--@elvariable id="news" type="java.util.List<News>"--%>
<c:forEach items="${news}" var="anew" varStatus="status">
    <ul>
        <li>
            Title <br> ${anew.title}
        </li>

        <li>
            Article <br> ${anew.text}
        </li>

        <li>
            Comments <br> ${anew.comment}
        </li>
    </ul>
</c:forEach>
<div class="tables">
    <h1>Таблица репозиториев</h1>
    <table>
        <tr>
            <td>Address</td>
            <td>Title news</td>
            <td>Admin login</td>
            <td>User login</td>
            <td>Действия</td>
        </tr>
        <%--@elvariable id="repositories" type="java.util.List<Repository>"--%>
        <c:forEach items="${repositories}" var="rep" varStatus="status">
            <tr>
                <td> ${rep.address}</td>
                <td> ${rep.news.title}</td>
                <td> ${rep.admin.login}</td>
                <td> ${rep.user.login}</td>
                <td>
                    <a href="<c:url value="/edit?login=${rep.address}"/>">Edit</a>
                    <a href="<c:url value="/delete?login=${rep.address}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>