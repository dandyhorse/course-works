<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.04.2016
  Time: 1:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<aside>

    <a href="index.jsp">На Главную</a>
    <br><br>


</aside>

<h1>Добавление</h1>

<script type="text/javascript">
    function OnDropDownChange(dropDown) {
        document.getElementById("selectedOption").value = dropDown.options[dropDown.selectedIndex].value;
    }
</script>

<form action="${pageContext.request.contextPath}/add"
      method="post"
      accept-charset="UTF-8">
    <select onChange="OnDropDownChange(this);" title="What to add">
        <option value="admin">Admin</option>
        <option value="user">User</option>
    </select>

    <input type="hidden" name="option" id="selectedOption" value="admin">
    <input type="text" name="login" title="login">
    <input type="password" name="password" title="password">
    <input type="submit">
    <p>${message}</p>

    <a href="<c:url value="/results"/>">Показать все данные</a>

</form>

</body>
</html>
