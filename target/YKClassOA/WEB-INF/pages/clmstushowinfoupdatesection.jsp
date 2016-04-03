<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
  <form:form action="/upstushowinfo" method="get" modelAttribute="stuEntity">
    <form:hidden path="id"/>
    <table id="t01">
      <tr>
        <th colspan="2">更新信息</th>
      </tr>
      <tr>
        <td>学号</td>
        <td><form:input path="stu_num"/></td>
      </tr>
      <tr>
        <td>姓名</td>
        <td><form:input path="stu_name"/></td>
      </tr>
      <tr>
        <td>电话</td>
        <td><form:input path="stu_tel"/></td>
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
