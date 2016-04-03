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
  <form action="/adduser" method="get">
  <table id="t01">
    <tr>
      <th colspan="2">添加用户</th>
    </tr>
    <tr>
      <td>用户名</td>
      <td><input type="text" name="username"/></td>
    </tr>
    <tr>
      <td>密码</td>
      <td><input type="text" name="passwd"/></td>
    </tr>
    <tr>
      <td>姓名</td>
      <td><input type="text" name="name_ch"/></td>
    </tr>
    <tr>
      <td>Email</td>
      <td><input type="text" name="email"/></td>
    </tr>
    <tr>
      <td>角色</td>
      <td>
      <select name="role">
        <option value="<%=AccountRoleConstant.ADMINISTRATOR%>">管理员</option>
        <option value="<%=AccountRoleConstant.CLASS_MASTER%>">班主任</option>
      </select>
      </td>
    </tr>
    <tr>
      <td>身份证</td>
      <td><input type="text" name="idCard"/></td>
    </tr>
    <tr>
      <td>部门</td>
      <td>
        <select name="dept">
          <option value="<%=DeptConstant.YK_DEAN_OFFICE%>">院长办公室</option>
          <option value="<%=DeptConstant.YK_VICE_DEAN_OFFICE%>">副院长办公室</option>
          <option value="<%=DeptConstant.YK_SECRETARY_OFFICE%>">书记办公室</option>
          <option value="<%=DeptConstant.YK_OFFICE%>">院办公室</option>
          <option value="<%=DeptConstant.YK_GENERAL_DEPT%>">综合办公室</option>
          <option value="<%=DeptConstant.YK_STUDENT_DEPT%>">学工办</option>
          <option value="<%=DeptConstant.YK_STUDY_DEPT%>">教务办</option>
          <option value="<%=DeptConstant.YK_YOUTH_COMMITTEE%>">团委</option>
          <option value="<%=DeptConstant.YK_ENGLISH_SECTION%>">英语教研室</option>
          <option value="<%=DeptConstant.YK_CHINESE_SECTION%>">语文教研室</option>
          <option value="<%=DeptConstant.YK_COMPUTER_SECTION%>">计算机教研室</option>
          <option value="<%=DeptConstant.YK_POLITICS_SECTION%>">政治教研室</option>
          <option value="<%=DeptConstant.YK_MATH_SECTION%>">数学教研室</option>
        </select>
      </td>
      <tr>
        <td>班级</td>
        <td><input type="text" name="classNo"></td>
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
