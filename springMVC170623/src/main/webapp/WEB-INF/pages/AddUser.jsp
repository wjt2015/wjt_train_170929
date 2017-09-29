<%--
  Created by IntelliJ IDEA.
  User: linux2014
  Date: 17-6-22
  Time: 下午10:50
  To change this template use File | Settings | File Templates.

  /springMVC170622_tomcat/AddUser
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored ="false" %>--%>
<html>
<head>
    <title>AddUser</title>
</head>
<body>
<form action="/springMVC170623_tomcat/AddUser.html" method="POST">
    <fieldset>
        <legend>创建用户</legend>
        <p>
            <label>姓名:</label>
            <input type="text" id="name_id" name="name" tabindex="1">
        </p>
        <p>
            <label>年龄:</label>
            <input type="text" id="age_id" name="age" tabindex="2">
        </p>
        <p>
            <label>昵称:</label>
            <input type="text" id="nickName_id" name="nickName" tabindex="3">
        </p>
        <p>
            <label>密码:</label>
            <input type="text" id="password_id" name="password" tabindex="4">
        </p>
        <p id="buttons-hbox">
            <input type="reset" id="cancel_id" value="取消" tabindex="5">
            <input type="submit" id="submit_id" value="确定" tabindex="6">
        </p>
    </fieldset>
</form>
</body>
</html>





