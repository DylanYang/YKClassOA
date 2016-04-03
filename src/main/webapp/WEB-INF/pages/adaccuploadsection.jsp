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
<%--<form action="accExcelIn"  enctype="multipart/form-data" method="get">--%>
  <%--<input type="file" name="filePath"/><br>--%>
  <%--<input type="submit" value="上传"/>--%>
<%--</form>--%>
<form method="POST" enctype="multipart/form-data" action="accExcelIn">
  选择文件: <input type="file" name="filePath"><br/>
  文件名称: <input type="text" name="note"><br/>
  <br/>
  <input type="submit" value="上传"> 上传文件!
</form>
</body>
</html>
