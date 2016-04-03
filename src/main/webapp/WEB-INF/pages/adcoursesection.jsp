<%@ page import="com.yk.model.cl.AccountEntityCL" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yk.model.CourseEntity" %>
<%@ page import="com.yk.model.cl.CourseEntityCL" %>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 12/2/15
  Time: 4:30 PM
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
    <th>课程号</th>
    <th>课程名</th>
    <th>学分</th>
    <th>修改</th>
    <th>删除</th>
  </tr>
  <tr>
  <%
    CourseEntityCL ccl = new CourseEntityCL();
    ArrayList arrList = (ArrayList)request.getAttribute("allCourses");
    for (int i = 0;i < arrList.size();i++){
    Object o = (Object)arrList.get(i);
  %>
  <tr>
    <td><%=((CourseEntity)(o)).getCourse_num()%></td>
    <td><%=((CourseEntity)o).getCourse_name()%></td>
    <td><%=((CourseEntity)o).getCredit()%></td>
    <td><form action="/upcoursepage" method="get"><input type="submit" value="更新"/><input type="hidden" value="<%=((CourseEntity)(o)).getId()%>"name="courseId"/></form></td>
    <td><form action="/delCourse" onsubmit="return delAllCourse()"><input type="submit" value="删除" onclick="delAllCourse()"/><input type="hidden" value="<%=((CourseEntity)(o)).getId()%>"name="courseId"/></form></td>
  </tr>
  <%}%>
  <%
    int pageNow = (Integer)request.getAttribute("pageNow");
    int pageSize = 5;
    int pageCount = ccl.getCoursePageCount(pageSize);
  %>
  <tr>
    <td colspan="11" align="center">
      <%if (pageNow!=0){%>
      <a href="/adCourseInfoPage?pageNow=<%=pageNow-1%>">上一页</a>
      <%}%>
      <% for (int i = 0;i <pageCount;i++){%>
      <a href="/adCourseInfoPage?pageNow=<%=i%>"><%=i+1%></a>
      <%}%>
      <%if (pageNow!=pageCount-1){%>
      <a href="/adCourseInfoPage?pageNow=<%=pageNow+1%>">下一页</a>
      <%}%>
    </td>
  </tr>
</table>
</body>
</html>
