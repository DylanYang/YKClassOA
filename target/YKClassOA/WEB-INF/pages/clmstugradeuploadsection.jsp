<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<p>
<h3>批量导入成绩！</h3></p>
<form method="POST" enctype="multipart/form-data" action="stuGradeExcelIn">
    选择文件: <input type="file" name="filePath"><br/>
    文件名称: <input type="text" name="note"><br/>
    <br/>
    <input type="hidden" value="<%= Integer.parseInt(request.getSession().getAttribute("classItemId").toString())%>" name="classItemId">
    <input type="submit" value="上传"> 上传文件!
</form>
</body>
</html>