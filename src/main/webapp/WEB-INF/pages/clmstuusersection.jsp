<%@ page import="com.yk.common.AccountRoleConstant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yk.model.cl.AccountEntityCL" %>
<%@ page import="com.yk.model.StudentEntity" %>
<%@ page import="com.yk.model.SelectedCourseEntity" %>
<%@ page import="com.yk.model.cl.CourseEntityCL" %>
<% String logErrStr = (String)request.getAttribute("logErrStr");%>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 11/25/15
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%CourseEntityCL ccl = new CourseEntityCL();%>
<html>
<head>
    <title></title>
</head>
<body>
  <section>
    <table id="t01">
      <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>班级</th>
        <th colspan="<%=ccl.getSubjectTotalCount()*4%>">成绩</th>
        <th>修改</th>
        <th>删除</th>
      </tr>
      <tr>
    <%
    AccountEntityCL acl = new AccountEntityCL();
    ArrayList arrList = (ArrayList)request.getAttribute("allStuInfo");
    for (int i = 0;i < arrList.size();i++){
  %>
      <tr>
        <td><%=((StudentEntity)(arrList.get(i))).getStu_num()%></td>
        <td><%=((StudentEntity)arrList.get(i)).getStu_name()%></td>
        <td><%=((StudentEntity)arrList.get(i)).getClassItem_id().getClassNo()%></td>
        <%
          for (SelectedCourseEntity sc : ((StudentEntity)arrList.get(i)).getSelectedCourse_id()) {
        %>
          <td><%=sc.getCourse_id().getCourse_name()%><input type="hidden" value="<%=sc.getCourse_id()%>" name="course_id"/></td>
          <td><%=sc.getOrd_score()%></td>
          <td><%=sc.getEnd_score()%></td>
          <td><%=sc.getFinal_result()%></td>
        <%
          }
        %>
        <td><form action="/upstushowinfopage" method="get"><input type="submit" value="更新"/><input type="hidden" value="<%=((StudentEntity)(arrList.get(i))).getId()%>"name="userId"/></form></td>
        <td><form action="/delUser" method="get"><input type="submit" value="删除" onclick="delAllAccount()"/><input type="hidden" value="<%=((StudentEntity)(arrList.get(i))).getId()%>"name="userId"/></form></td>
      </tr>
      <%}%>
      <%

        int pageNow = (Integer)request.getAttribute("pageNow");

        int pageSize = 5;
        int pageCount = acl.getAccountPageCount(pageSize);

      %>
    </table>
  </section>
</body>
</html>
