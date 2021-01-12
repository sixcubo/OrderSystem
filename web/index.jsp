<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/11
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  //定义绝对路径地址
  String contextPath = request.getContextPath();
%>

<html>
  <head>
    <title>xx酒店欢迎您</title>
    <link rel="stylesheet" href="/css/index/index.css">
  </head>

  <%--导入jquery--%>
  <script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>

  <script>
      function toCustomerLogin(){
        location.href="http://127.0.0.1:8080/jsp/login/customer_login.jsp";
      }

      function toManagerLogin(){
        location.href="http://127.0.0.1:8080/jsp/login/manager_login.jsp";
      }
  </script>

  <body>
  <div class="box">
    <form>
      <tr>
        <td>
          <input type="button" onclick="toCustomerLogin()" value="顾客登录">
        </td>
        <td>
          <input type="button" onclick="toManagerLogin()" value="商家访问">
        </td>
      </tr>
    </form>
  </div>
  </body>
</html>
