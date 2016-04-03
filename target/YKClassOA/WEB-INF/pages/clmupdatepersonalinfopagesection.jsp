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
  <form:form action="/clmUpdatePersonalInfo" method="get" modelAttribute="account">
    <form:hidden path="id"/>
    <table id="t01">
      <tr>
        <th colspan="2">更新信息</th>
      </tr>
      <tr>
        <td>用户名</td>
        <td><form:input path="username"/></td>
      </tr>
      <tr>
        <td>密码</td>
        <td><form:input path="password"/></td>
      </tr>
      <tr>
        <td>姓名</td>
        <td><form:input path="name_ch"/></td>
      </tr>
      <tr>
        <td>Email</td>
        <td><form:input path="email"/></td>
      </tr>
      <tr>
        <td>角色</td>
        <td>
          <form:select path="role" name="role">
            <form:option value="<%=AccountRoleConstant.ADMINISTRATOR%>">管理员</form:option>
            <form:option value="<%=AccountRoleConstant.CLASS_MASTER%>">班主任</form:option>
          </form:select>
        </td>
      </tr>
      <tr>
        <td>身份证</td>
        <td><form:input path="id_card"/></td>
      </tr>
      <%AccountEntityCL acl = new AccountEntityCL();%>
      <tr>
        <td>部门</td>
        <td>
          <form:select path="dept" name="dept">
            <%--<form:options items="${dept}" itemValue="code" itemLabel="name"/>--%>
            <form:option value="<%=DeptConstant.YK_DEAN_OFFICE%>">院长办公室</form:option>
            <form:option value="<%=DeptConstant.YK_VICE_DEAN_OFFICE%>">副院长办公室</form:option>
            <form:option value="<%=DeptConstant.YK_SECRETARY_OFFICE%>">书记办公室</form:option>
            <form:option value="<%=DeptConstant.YK_OFFICE%>">院办公室</form:option>
            <form:option value="<%=DeptConstant.YK_GENERAL_DEPT%>">综合办公室</form:option>
            <form:option value="<%=DeptConstant.YK_STUDENT_DEPT%>">学工办</form:option>
            <form:option value="<%=DeptConstant.YK_STUDY_DEPT%>">教务办</form:option>
            <form:option value="<%=DeptConstant.YK_YOUTH_COMMITTEE%>">团委</form:option>
            <form:option value="<%=DeptConstant.YK_ENGLISH_SECTION%>">英语教研室</form:option>
            <form:option value="<%=DeptConstant.YK_CHINESE_SECTION%>">语文教研室</form:option>
            <form:option value="<%=DeptConstant.YK_COMPUTER_SECTION%>">计算机教研室</form:option>
            <form:option value="<%=DeptConstant.YK_POLITICS_SECTION%>">政治教研室</form:option>
            <form:option value="<%=DeptConstant.YK_MATH_SECTION%>">数学教研室</form:option>
          </form:select>
        </td>
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
