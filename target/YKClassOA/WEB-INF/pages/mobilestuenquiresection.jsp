<%@ page import="com.yk.model.StudentEntity" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.yk.model.SelectedCourseEntity" %>
<%@ page import="com.yk.model.cl.CourseEntityCL" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=320,user-scalable=false" />
    <link rel="stylesheet" type="text/css" href="css/mobilestyle.css">
    <%CourseEntityCL ccl = new CourseEntityCL();%>
</head>
<body>
<section>
    <%
        StudentEntity se = (StudentEntity)request.getAttribute("stuEntity");
    %>
    <table id="t01" border="1">
        <tr>
            <th>姓名</th>
            <%--<th>班级</th>--%>
            <%--<th colspan="<%=ccl.getSubjectTotalCount()*4%>">成绩</th>--%>
            <%
                for (SelectedCourseEntity sc1:se.getSelectedCourse_id()){
            %>
            <th><%=sc1.getCourse_id().getCourse_name()%></th>
            <%
                }
            %>

        </tr>
        <tr>
            <td><%=se.getStu_name()%></td>
            <%--<td><%=se.getClassItem_id().getClassNo()%></td>--%>
        <%
            for (SelectedCourseEntity sc:se.getSelectedCourse_id()){
        %>
                <td><%=sc.getFinal_result()%></td>
        <%
            }
        %>
    </table>
</section>
</body>
</html>
