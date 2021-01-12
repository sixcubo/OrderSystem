<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/11/011
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/dish" method="post"  enctype="multipart/form-data">
      商品展示<input type="file" name="file" id="file">
      <input type="submit" name="Submit" value="上传">
    </form>
  </body>
</html>
