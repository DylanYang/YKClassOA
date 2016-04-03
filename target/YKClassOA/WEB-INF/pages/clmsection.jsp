<%@ page import="com.yk.common.AccountRoleConstant" %>
<%@ page import="com.yk.model.AccountEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yk.model.ClassItemEntity" %>
<% String logErrStr = (String)request.getAttribute("logErrStr");%>
<%
  ArrayList alist = (ArrayList)request.getAttribute("accInfolist");
  request.getSession().setAttribute("classItemId",((ClassItemEntity)alist.get(1)).getId());
%>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 11/25/15
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <section>
    <h1>欢迎<%=((ClassItemEntity)alist.get(1)).getClassNo()%>班 班主任<%=((AccountEntity)alist.get(0)).getName_ch()%> </h1>
    <h1>您的工号<%=request.getSession().getAttribute("staffId")%></h1>
  </section>
</body>
</html>
