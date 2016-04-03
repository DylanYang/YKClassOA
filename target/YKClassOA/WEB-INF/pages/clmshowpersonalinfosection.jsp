<%@ page import="com.yk.model.AccountEntity" %>
<%@ page import="com.yk.model.cl.AccountEntityCL" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 11/26/15
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table id="t01">
  <tr>
    <th>员工号</th>
    <th>用户名</th>
    <th>密码</th>
    <th>姓名</th>
    <th>创建时间</th>
    <th>Email</th>
    <th>角色</th>
    <th>ID-Card</th>
    <th>部门</th>
    <th>班级</th>
    <th>修改</th>
  </tr>
  <tr>
  <%
    AccountEntityCL acl = new AccountEntityCL();
    AccountEntity ae = (AccountEntity)request.getAttribute("account");
  %>
  <tr>
    <td><%=ae.getUsername()%></td>
    <td><%=ae.getUsername()%></td>
    <td><%=ae.getPassword()%></td>
    <td><%=ae.getName_ch()%></td>
    <td><%=ae.getCreate_time()%></td>
    <td><%=ae.getEmail()%></td>
    <td><%=acl.getRoleChinese(ae.getRole())%></td>
    <td><%=ae.getId_card()%></td>
    <td><%=acl.getDeptChinese(ae.getDept())%></td>
    <td><%=ae.getTeacher_id().getClassItem_id().getClassNo()%></td>
    <td><form action="/clmUpdatePersonalInfoPage" method="get"><input type="submit" value="更新"/><input type="hidden" value="<%=ae.getId()%>"name="accountId"/></form></td>
  </tr>
</table>
</body>
</html>