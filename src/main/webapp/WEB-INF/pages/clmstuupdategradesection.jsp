<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.yk.common.AccountRoleConstant" %>
<%@ page import="com.yk.model.AccountEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yk.model.ClassItemEntity" %>
<%@ page import="com.yk.model.CourseEntity" %>
<%@ page import="com.yk.model.cl.CourseEntityCL" %>
<%@ page import="com.yk.model.SelectedCourseEntity" %>
<% String logErrStr = (String)request.getAttribute("logErrStr");%>
<% ArrayList alist = (ArrayList)request.getAttribute("accInfolist");%>
<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 11/25/15
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  CourseEntityCL ccl = new CourseEntityCL();
  int colspanCount = ccl.getSubjectTotalCount()*4;
%>
<html>
<head>
    <title></title>
</head>
<body>
  <section>
    <form:form action="/stuAddGrade" method="get" modelAttribute="stuEntity">
      <table id="t01">
        <tr>
          <th colspan="9">添加成绩</th>
        </tr>
        <tr>
          <td>学号</td>
          <td colspan="8"><form:input path="stu_num"/></td>
        </tr>
        <tr>
          <td >姓名</td>
          <td colspan="8"><form:input path="stu_name"/></td>
        </tr>
        <%
          ArrayList<CourseEntity> cList = ccl.getAllCourses();
          for (CourseEntity ce:cList){
        %>
        <tr>
          <td colspan="2"><%=ce.getCourse_name()%></td>
          <td>平时成绩</td>
          <td><form:input path="ord_score"/></td>
          <td>卷面成绩</td>
          <td><form:input path="end_score"/></td>
          <td>综合成绩</td>
          <td><form:input path="final_result"/></td>
        </tr>
        <%
          }
        %>
        <tr>
          <td>提交</td>
          <td colspan="8"><input type="hidden" value="1" name="pageNow"><input type="hidden" value="<%=request.getSession().getAttribute("staffId")%>" name="staffId"><input type="submit" value="提交"/></td>
        </tr>
      </table>
    </form:form>
  </section>
</body>
</html>
