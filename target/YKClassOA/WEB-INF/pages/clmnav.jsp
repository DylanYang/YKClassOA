<%--
  Created by IntelliJ IDEA.
  User: dylanyang
  Date: 11/26/15
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<a href="/clmShowPersonalInfoPage?staffId=<%=request.getSession().getAttribute("staffId")%>">个人信息</a>
<a href="/stuShowInfo?pageNow=0">查看学生</a>
<a href="/stuAddStuPage">添加学生</a>
<a href="/stuGradeInfoPaging?pageNow=0&staffId=<%=request.getSession().getAttribute("staffId")%>">查看学生成绩</a>
<a href="/stuAddGradePage">添加成绩</a>
<a href="stuShowInfoUpLoadPage">批量导入学生</a>
<a href="stuGradeUploadPage">批量导人成绩</a>
<a href="stuDelAllPage">批量删除</a>
<a href="/clmquit">退出系统</a><br>
</nav>
</body>
</html>
