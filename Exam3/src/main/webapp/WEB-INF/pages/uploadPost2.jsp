<%--
  Created by IntelliJ IDEA.
  User: linux2014
  Date: 17-6-15
  Time: 下午6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/dev_Exam3_tomcat/uploadPost.mvc" method="post" enctype="multipart/form-data">
    <input type="file" name="source" >
    <input type="file" name="target" >
    <input type="submit">
</form>

<h3>最近5条历史对比结果</h3>
<table>
    <tr>
        <th>对比时间</th>
        <th>源文件内容</th>
        <th>目标文件内容</th>
        <th>差异</th>
        <th>操作</th>
    </tr>

    <tr>
        <td>${date}</td>
        <td>${srcText}</td>
        <td>${targetText}</td>
        <td>${diffText}</td>
        <td>操作</td>
    </tr>

</table>

</body>
</html>
