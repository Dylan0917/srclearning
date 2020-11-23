<%--
  Created by IntelliJ IDEA.
  User: yu.wenhua
  Date: 2020/11/19
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String id  = (String) request.getAttribute("id");
String sessionCreateTime  = (String) request.getAttribute("sessionCreateTime");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>SpringMVC中使用原生API<br>请求参数为：<b><%=id%></b>
<br>
sessionCreateTime:  <%=sessionCreateTime%>
</h2>
</body>
</html>
