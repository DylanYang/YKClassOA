<%@ page import="com.yk.model.UserEntity" %>
<%@ page import="com.yk.model.StudentEntity" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <% for(UserEntity ue:(List<UserEntity>)request.getAttribute("users")){%>
    <%out.print(ue.getName());%>
    <%}%>
</body>
</html>