<%--
  Created by IntelliJ IDEA.
  User: yu.wenhua
  Date: 2020/11/12
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.5.1/jquery-3.5.1.js"></script>
</head>
<script type="text/javascript">
    function sendAjaxtest() {
        // alert("fdfd");
          var params = '{"uid": "1","userName": "测试商品"}';
          $.ajax({
              type:"post",
              url:"/s01/c02/m01",
              // dataType:'json',
             contentType:"application/json;charset=UTF-8",
             data:JSON.stringify(params),//方法用于将 JavaScript 值转换为 JSON 字符串
              success:function (data) {
                  alert(data.name);
              },error:function (d) {
                  alert(d);
              }
          });
    }
    function sendAjaxtest01() {
        // alert("fdfd");
          var params = '{"uid": "1","userName": "测试商品"}';
          $.ajax({
              type:"GET",
              url:"${pageContext.request.contextPath}/c01/m02",
              success:function (data) {
                  alert(data.name);
              }
          });
    }
</script>
<body>
<button id="afaf" type="button" value="点一下" onclick="sendAjaxtest()">点一下</button>
<button id="afaf1" type="button" value="点一下" onclick="sendAjaxtest01()">点一下01</button>
</body>
</html>
