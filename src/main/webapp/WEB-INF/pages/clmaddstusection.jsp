<% String logErrStr = (String)request.getAttribute("logErrStr");%>
<% int  classItemId = Integer.parseInt(request.getSession().getAttribute("classItemId").toString());%>
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
    <form action="/stuAddStu" method="get">
        <table id="t01">
            <tr>
                <th colspan="2">添加学生</th>
            </tr>
            <tr>
                <td>学号</td>
                <td><input type="text" name="stu_no"/></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="stu_name"/></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="stu_tel"/><input type="hidden" name="classItemId" value="<%=classItemId%>"/></td>
            </tr>
            <tr>
                <td>提交</td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>
</section>
</body>
</html>
