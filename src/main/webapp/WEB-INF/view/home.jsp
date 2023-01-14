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

<html>
<head>
    <title>Home</title>
</head>
<body>
  <p>Hello Ectimel!</p>
<form:form action="${pageContext.request.contextPath}/logout" method="POST">

    <input type="submit" value="Logout" />
</form:form>
</body>
</html>
