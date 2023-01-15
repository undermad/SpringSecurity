<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik
  Date: 13.01.2023
  Time: 03:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
<p>Hello Ectimel!</p>
User: <security:authentication property="principal.username"/> <br/>
Roles: <security:authentication property="principal.authorities"/>
<p>
    <a href="${pageContext.request.contextPath}/moderator">Moderator only</a>
</p>
<p>
    <a href="${pageContext.request.contextPath}/system">Admin only</a>
</p>


<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
