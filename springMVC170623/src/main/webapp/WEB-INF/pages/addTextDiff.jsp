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
<h1>${message}</h1>
<h2>spring入门经典!!</h2>
<h2>${textDiff}</h2>
<form action="/springMVC170623_tomcat/addTextDiff.html" method="POST">
    <fieldset>
        <legend>创建TextDiff</legend>
        <%--<p>--%>
            <%--<label>日期:</label>--%>
            <%--<input type="text" id="date_id" name="date" tabindex="1">--%>
        <%--</p>--%>
        <p>
            <label>源文件内容:</label>
            <input type="text" id="src_id" name="srcText" tabindex="2">
        </p>
        <p>
            <label>目标文件内容:</label>
            <input type="text" id="target_id" name="targetText" tabindex="3">
        </p>
        <p>
            <label>两个文件的差异:</label>
            <input type="text" id="diff_id" name="diffText" tabindex="4">
        </p>
        <p id="buttons-hbox">
            <input type="reset" id="cancel_id" value="取消" tabindex="5">
            <input type="submit" id="submit_id" value="确定" tabindex="6">
        </p>
    </fieldset>
</form>
</body>
</html>

