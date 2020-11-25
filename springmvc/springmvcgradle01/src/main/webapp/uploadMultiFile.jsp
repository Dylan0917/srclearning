<%--
  Created by IntelliJ IDEA.
  User: yu.wenhua
  Date: 2020/11/24
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload multi file</title>
</head>
<body>
<h3>upload multi file</h3>
<form method="post" action="${pageContext.request.contextPath}/c03/m02" enctype="multipart/form-data">
    upload to file(1):<input type="file" name="file"><br>
    upload to file(2):<input type="file" name="file"><br>
    upload to file(3):<input type="file" name="file"><br>
    upload to file(4):<input type="file" name="file"><br>
    upload to file(5):<input type="file" name="file"><br>
    <input type="submit" value="upload">
</form>
</body>
</html>
