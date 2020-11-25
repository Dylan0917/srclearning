<%--
  Created by IntelliJ IDEA.
  User: yu.wenhua
  Date: 2020/11/24
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/c03/m01" method="post" enctype="multipart/form-data" >
    <input type="file" name="photo">
    <input type="text" name="username">
    <input type="submit" value="提交">
</form>
</body>
</html>
