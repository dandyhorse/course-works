<%@ page import="java.util.List" %>
<%@ page import="hibernate.entity.Repository" %>
<%@ page import="hibernate.entity.News" %>
<%@ page import="hibernate.entity.Admin" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.04.2016
  Time: 1:04
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
    <%List<Admin> admins = (List<Admin>) request.getAttribute("admins");%>
    <%List<News> news = (List<News>) request.getAttribute("news");%>
    <%List<Repository> repositories = (List<Repository>) request.getAttribute("repositories");%>
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
        </tr>
        <% for (Admin admin : admins) { %>
        <tr>
            <td><%=admin.getLogin()%>
            </td>
            <td><%=admin.getPassword()%>
            </td>
        </tr>
    </table>
    <%}%>
</div>
<h1>Колонка Новостей</h1>
<% for (News aNew : news) { %>
<ul>
    <li> Title<br> <%=aNew.getTitle()%>
    </li>

    <li>Article<br> <%=aNew.getText()%>
    </li>

    <li>Comments<br> <%=aNew.getComment()%>
    </li>
</ul>
<%}%>
<div class="tables">
    <h1>Таблица репозиториев</h1>
    <table>
        <tr>
            <td>Address</td>
            <td>Title news</td>
            <td>Admin login</td>
            <td>User login</td>
        </tr>
        <% for (Repository repository : repositories) { %>
        <tr>
            <td><%=repository.getAddress()%>
            </td>
            <td><%=repository.getNews()%>
            </td>
            <td><%=repository.getAdmin()%>
            </td>
            <td><%=repository.getUser()%>
            </td>
        </tr>
    </table>
    <%}%>
</div>
</body>
</html>