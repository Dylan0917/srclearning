<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World! Fighting!</h2>
<form action="${pageContext.request.contextPath}/c01/m01">
    <input type="submit" value="提交c01/m01">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m603">
    <input type="submit" value="提交/c01/m603">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/meree03">
    <input type="submit" value="提交/c01/meree03">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m/fdf/fdf/ddd/03">
    <input type="submit" value="提交/c01/m/fdf/fdf/ddd/03">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m06/12" method="get">
    <input type="submit" value="rest提交(GET)/c01/m06/12">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m06/12" method="post">
    <input type="submit" value="rest提交(POST)/c01/m06/12">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m08" method="post">
    <b>提交参数</b><input type="text" name="id">
    <input type="submit" value="rest提交(POST)/c01/m08">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m09" method="post">
    <b>提交参数</b><input type="text" name="id">
    <input type="submit" value="rest提交(POST)/c01/m09">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m10" method="post">
    <b>数组</b>
    <input type="checkbox" name="hobbies" value="lq">篮球
    <input type="checkbox" name="hobbies" value="ppq">乒乓球
    <input type="checkbox" name="hobbies" value="pq">皮球
    <input type="checkbox" name="hobbies" value="tq">台球
    <input type="submit" value="提交(POST)/c01/m10">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m11" method="post">
    <b>Pojo对象</b>
    <input type="text" name="uid" >
    <input type="text" name="userName">
    <input type="submit" value="提交(POST)/c01/m11">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m12" method="post">
    <b>使用map向页面传递数据</b>
    <input type="submit" value="提交(POST)/c01/m12">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m13" method="post">
    <b>使用ModelAndView向页面传递数据</b>
    <input type="submit" value="提交(POST)/c01/m13">
</form>
<h2>-------------------------------------</h2>
<form action="${pageContext.request.contextPath}/c01/m14" method="post">
    <b>spring全局类型转换器</b>
    日期<input type="text" name="inDate">
    <input type="submit" value="提交(POST)/c01/m14">
</form>
</body>
</html>
