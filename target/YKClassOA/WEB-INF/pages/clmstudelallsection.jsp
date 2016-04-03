<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yk.model.StudentEntity" %>
<%@ page import="com.yk.model.cl.StudentEntityCL" %>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 12/2/15
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

%>
<html>
<head>
    <title></title>
</head>
<body>
<table id="t01">
    <tr>
        <th>删除所有学生</th>
        <%--<th>删除所有成绩</th>--%>
    </tr>
    <tr>
        <td><form action="/delAllStudent" method="get" onsubmit="return delAllStu()"><input type="submit" value="删除"/></form></td>
        <%--<td><form action="#" method="get"><input type="submit" value="删除"/></form></td>--%>
    </tr>
</table>
</body>
</html>
