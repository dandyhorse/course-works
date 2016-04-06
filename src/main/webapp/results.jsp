<%@ page import="hibernate.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="hibernate.entity.Repository" %>
<%@ page import="hibernate.entity.News" %>
<%@ page import="hibernate.entity.Admin" %><%--
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

    <a href="${pageContext.request.contextPath}/add">Добавить</a>
    <br><br>
    <a href="index.jsp">Назад</a>

</aside>

<div class="tables">
    <%List<User> users = (List<User>) request.getAttribute("users");%>
    <%List<Admin> admins = (List<Admin>) request.getAttribute("admins");%>
    <%List<News> news = (List<News>) request.getAttribute("news");%>
    <%List<Repository> repositories = (List<Repository>) request.getAttribute("repositories");%>
    <h1>Таблица Пользователей</h1>
    <table>
        <tr>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <% for (User user : users) { %>
        <tr>
            <td><%=user.getLogin()%>
            </td>
            <td><%=user.getPassword()%>
            </td>
        </tr>
    </table>
    <%}%>
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
    <li> Title<br> <%=aNew.getTitle_news()%>
    </li>
    
    <li>Article<br> <%=aNew.getText()%>
    </li>

    <li>Comments<br> <%=aNew.getComent()%>
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
            <td><%=repository.getAdress()%>
            </td>
            <td><%=repository.getTitle_news()%>
            </td>
            <td><%=repository.getLogin_adm()%>
            </td>
            <td><%=repository.getLogin_user()%>
            </td>
        </tr>
    </table>
    <%}%>
</div>
</body>
</html>