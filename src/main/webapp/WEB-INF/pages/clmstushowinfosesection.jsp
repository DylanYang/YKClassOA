<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yk.model.StudentEntity" %>
<%@ page import="com.yk.model.cl.StudentEntityCL" %>
<%@ page import="com.yk.model.cl.ClassItemEntityCL" %>
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
    <th>学号</th>
    <th>姓名</th>
    <th>电话</th>
    <th>修改</th>
    <th>删除</th>
  </tr>
  <tr>
  <%
    StudentEntityCL stuCl = new StudentEntityCL();
    ArrayList<StudentEntity> stuList = (ArrayList<StudentEntity>)request.getAttribute("stuList");
    for (int i = 0;i < stuList.size();i++){
    Object o = (Object)stuList.get(i);
  %>
  <tr>
    <td><%=((StudentEntity)(o)).getStu_num()%></td>
    <td><%=((StudentEntity)o).getStu_name()%></td>
    <td><%=((StudentEntity)o).getStu_tel()%></td>
    <td><form action="/upstushowinfopage" method="get"><input type="submit" value="更新"/><input type="hidden" value="<%=((StudentEntity)(o)).getId()%>"name="stuId"/></form></td>
    <td><form action="/delStudent" onsubmit="return delStu()"><input type="submit" value="删除"/><input type="hidden" value="<%=((StudentEntity)(o)).getId()%>"name="stuId"/></form></td>
  </tr>
  <%}%>
  <%
    int pageNow = (Integer)request.getAttribute("pageNow");
    int pageSize = 5;
    ClassItemEntityCL cItemCL = new ClassItemEntityCL();
    int classItemId = cItemCL.getClassIdFromSessionRequest("classItemId",request);
    String classNo = cItemCL.getClassNoByClassItemId(classItemId);
    StudentEntityCL stuCL = new StudentEntityCL();
    int pageCount = stuCl.getStuInfoPageCount(pageSize,classNo);
  %>
  <tr>
    <td colspan="11" align="center">
      <%if (pageNow!=0){%>
      <a href="/stuShowInfo?pageNow=<%=pageNow-1%>">上一页</a>
      <%}%>
      <% for (int i = 0;i <pageCount;i++){%>
      <a href="/stuShowInfo?pageNow=<%=i%>"><%=i+1%></a>
      <%}%>
      <%if (pageNow!=pageCount-1){%>
      <a href="/stuShowInfo?pageNow=<%=pageNow+1%>">下一页</a>
      <%}%>
    </td>
  </tr>
</table>
</body>
</html>
