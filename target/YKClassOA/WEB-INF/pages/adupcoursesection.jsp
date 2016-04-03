<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.yk.common.AccountRoleConstant" %>
<%@ page import="com.yk.common.DeptConstant" %>
<%@ page import="com.yk.model.cl.AccountEntityCL" %>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 11/28/15
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<section>
  <form:form action="/adupcourse" method="get" modelAttribute="courses">
    <form:hidden path="id"/>
    <table id="t01">
      <tr>
        <th colspan="2">更新信息</th>
      </tr>
      <tr>
        <td>课程号</td>
        <td><form:input path="course_num"/></td>
      </tr>
      <tr>
        <td>课程名</td>
        <td><form:input path="course_name"/></td>
      </tr>
      <tr>
        <td>学分</td>
        <td><form:input path="credit"/></td>
      </tr>
      <tr>
        <td>提交</td>
        <td><input type="submit" value="提交"/></td>
      </tr>
      </tr>
    </table>
  </form:form>
</section>
</body>
</html>
