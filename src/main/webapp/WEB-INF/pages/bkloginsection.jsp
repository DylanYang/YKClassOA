<%@ page import="com.yk.common.AccountRoleConstant" %>
<% String logErrStr = (String)request.getAttribute("logErrStr");%>
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
  <nav>
    管理员<br>
    班主任<br>
    维护员<br>
  </nav>

  <section>
    <form action="/checkuser" method="post">
      <table id="t01">
        <tr>
          <th colspan="2">用户登录</th>
        </tr>
        <tr>
          <td>登录类型</td>
          <td>
            <select name="acctype">
              <option value="<%=AccountRoleConstant.ADMINISTRATOR%>">管理员</option>
              <option value="<%=AccountRoleConstant.CLASS_MASTER%>">班主任</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>用户名</td>
          <td>
            <input type="text" name="username"/>
          </td>
        </tr>
        <tr>
          <td>密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
          <td><input type="password" name="passwd"/></td>
        </tr>
        <tr>
          <td>提交</td>
          <td><input type="submit" name="go" value="提交"/></td>
        </tr>
        </table>
        <div><h2 class="bkLogErr"><% if (logErrStr != null)out.println(logErrStr);%></h2></div>
    </form>
  </section>
</body>
</html>
