<%--
  Created by IntelliJ IDEA.
  User: linux2014
  Date: 17-6-15
  Time: 下午6:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<script>
    function deleteDiffTextById(id) {
        var url = "/dev_Exam3_tomcat/deleteDiffText/" + id;
        document.location.href = url;
    }
</script>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <form action="/dev_Exam3_tomcat/login.mvc">
        <tr>
            <label>用户名</label>
            <input type="text" name="name">
            <label>密码</label>
            <input type="password" name="password">
        </tr>
        <tr>
            <input type="submit">
        </tr>
    </form>

    <form action="/dev_Exam3_tomcat/uploadPost.mvc" method="post" enctype="multipart/form-data">
        <input type="file" name="source">
        <input type="file" name="target">
        <input type="submit">
    </form>

    <h3>最近5条历史对比结果</h3>
    <table border="1">
        <thead>
        <tr>
            <th>对比时间</th>
            <th>源文件内容</th>
            <th>目标文件内容</th>
            <th>文件差异</th>
            <%--<th>操作</th>--%>
            <c:if test="${isLogin}">
                <th>操作</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="textDiff" items="${textDiffList}">
            <tr>
                <td>${textDiff.date}</td>
                <td>${textDiff.srcText}</td>
                <td>${textDiff.targetText}</td>
                <td>${textDiff.diffText}</td>
                <%--<c:if test="${isLogin}">--%>
                    <%--<td>--%>
                        <%--<form action="/dev_Exam3_tomcat/delete.mvc">--%>
                            <%--<input type="button" value="删除" >--%>
                        <%--</form>--%>
                    <%--</td>--%>
                <%--</c:if>--%>
                <c:if test="${isLogin}">
                    <td align="center"><a href="/dev_Exam3_tomcat/delete/${textDiff.id}.mvc">删除</a>
                    </td>
                </c:if>
            </tr>

        </c:forEach>
        </tbody>
    </table>

    <c:if test="${hasPrevPage}">
        <tr>
            <td align="center" colspan="5">
                <a href="/dev_Exam3_tomcat/prePage.mvc">上一页</a>
            </td>
        </tr>
    </c:if>

    <c:if test="${hasNextPage}">
        <tr>
            <td align="center" colspan="5">
                <a href="/dev_Exam3_tomcat/nextPage.mvc">显示更多</a>
            </td>
        </tr>
    </c:if>
</div>
</body>
</html>
