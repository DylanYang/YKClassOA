<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=320,user-scalable=false" />
    <link rel="stylesheet" type="text/css" href="css/mobilestyle.css">
</head>
<section>
    <h1>学生登录</h1>
    <h5>用户名是学号+身份证后六位</h5>
    <h5>密码与用户名相同</h5>
    <form action="/mStuEnquireGrade" method="get">
    <%--<form action="#" method="get">--%>
        用户名:
        <input type="text" name="stuNo"/>
        <br>
        密&nbsp;&nbsp;&nbsp;&nbsp;码:
        <input type="password" name="passwd"/>
        <br>
        查&nbsp;&nbsp;&nbsp;&nbsp;询:
        <input type="submit" name="go" value="查询"/>
    </form>
</section>
</body>
</html>
