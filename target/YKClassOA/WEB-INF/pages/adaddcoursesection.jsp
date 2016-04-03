<%@ page import="com.yk.common.AccountRoleConstant" %>
<%@ page import="com.yk.common.DeptConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 11/25/15
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="css/backendstyle.css"/>
</head>
<body>
<section>
  <form action="/adaddcourse" method="get">
  <table id="t01">
    <tr>
      <th colspan="2">添加课程</th>
    </tr>
    <tr>
      <td>课程号</td>
      <td><input type="text" name="course_num"/></td>
    </tr>
    <tr>
      <td>课程名</td>
      <td><input type="text" name="course_name"/></td>
    </tr>
    <tr>
      <td>学分</td>
      <td><input type="text" name="credit"/></td>
    </tr>
    <tr>
      <td>提交</td>
      <td><input type="submit" value="提交"/></td>
    </tr>
    </tr>
  </table>
  </form>
</section>
</body>
</html>
