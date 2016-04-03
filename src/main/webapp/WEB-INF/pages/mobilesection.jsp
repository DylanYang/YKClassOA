<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=320,user-scalable=false" />
    <link rel="stylesheet" type="text/css" href="css/mobilestyle.css">
</head>
<body>
<section>
    <h1>学生登录</h1>
    <form action="/mStuEnquireGrade" method="get">
    <%--<form action="#" method="get">--%>
        学号:<br>
        <input type="text" name="stuNo"/>
        <br>
        密码:<br>
        <input type="password" name="passwd"/>
        <br>
        查询:<br>
        <input type="submit" name="go" value="查询"/>
    </form>
</section>
</body>
</html>
