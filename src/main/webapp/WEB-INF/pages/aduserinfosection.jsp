<%@ page import="com.yk.model.AccountEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yk.model.cl.AccountEntityCL" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yk.model.TeacherEntity" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.yk.model.ClassItemEntity" %>
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
    <th>删除</th>
  </tr>
  <%--<c:forEach items="${allAccount}" var="u">--%>
    <%--<tr>--%>
      <%--<td><c:out value="${u.username}"/></td>--%>
      <%--<td><c:out value="${u.password}"/></td>--%>
      <%--<td><c:out value="${u.name_ch}"/></td>--%>
      <%--<td><c:out value="${u.create_time}"/></td>--%>
      <%--<td><c:out value="${u.email}"/>${u.email}</td>--%>
      <%--<td><c:out value='${u.role}'/></td>--%>
      <%--<td><c:out value="${u.id_card}"/></td>--%>
      <%--<td><c:out value="${u.dept}"/></td>--%>

    <%--</tr>--%>
  <%--</c:forEach>--%>
  <tr>
  <%
    AccountEntityCL acl = new AccountEntityCL();
    ArrayList arrList = (ArrayList)request.getAttribute("allAccount");
    for (int i = 0;i < arrList.size();i++){
    Object[] o = (Object[])arrList.get(i);
  %>
  <tr>
    <td><%=((TeacherEntity)(o[1])).getStaffId()%></td>
    <td><%=((AccountEntity)o[0]).getUsername()%></td>
    <td><%=((AccountEntity)o[0]).getPassword()%></td>
    <td><%=((AccountEntity)o[0]).getName_ch()%></td>
    <td><%=((AccountEntity)(o[0])).getCreate_time()%></td>
    <td><%=((AccountEntity)o[0]).getEmail()%></td>
    <td><%=acl.getRoleChinese(((AccountEntity)o[0]).getRole())%></td>
    <td><%=((AccountEntity)(o[0])).getId_card()%></td>
    <td><%=acl.getDeptChinese(((AccountEntity) (o[0])).getDept())%></td>
    <td><%=((ClassItemEntity)o[2]).getClassNo()%></td>
    <td><form action="/upuserpage" method="get"><input type="submit" value="更新"/><input type="hidden" value="<%=((AccountEntity)(o[0])).getId()%>"name="userId"/></form></td>
    <td><form action="/delUser" onsubmit="return delAllAccount()"><input type="submit" value="删除"/><input type="hidden" value="<%=((AccountEntity)(o[0])).getId()%>"name="userId"/></form></td>
  </tr>
  <%}%>
  <%

    int pageNow = (Integer)request.getAttribute("pageNow");

    int pageSize = 5;
    int pageCount = acl.getAccountPageCount(pageSize);

  %>
  <tr>
    <td colspan="12" align="center">
      <%if (pageNow!=0){%>
      <a href="/accountPaging?pageNow=<%=pageNow-1%>">上一页</a>
      <%}%>
      <% for (int i = 0;i <pageCount;i++){%>
          <a href="/accountPaging?pageNow=<%=i%>"><%=i+1%></a>
      <%}%>
      <%if (pageNow!=pageCount-1){%>
      <a href="/accountPaging?pageNow=<%=pageNow+1%>">下一页</a>
      <%}%>
    </td>
  </tr>
</table>
</body>
</html>