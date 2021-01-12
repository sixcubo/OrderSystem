<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/11
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //定义绝对路径地址
    String contextPath = request.getContextPath();
%>

<html>
<head>
    <meta charset="utf-8">
    <title>欢迎您登录</title>
    <link rel="stylesheet" href="../../css/manager_login/manager_login.css">
</head>

<%--导入jquery--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>

    function tologin(){
        $.ajax({
            url : "http://127.0.0.1:8080/merchant?method=login",
            type : "post",
            data : {
                username : $("[name='username']").val(),
                password : $("[name='password']").val(),
            },

            success:function (obj)
            {
                if(obj){
                    alert("登录成功！");
                    location.href="http://127.0.0.1:8080/jsp/manage/index.jsp";
                }else{
                    alert("登录失败，请重试，或联系管理员！")
                }
            },

            dataType : "json"
        })
    }
</script>

<body>
<div class="box">
    <h2>管理系统登录</h2>
    <form>
        <div class="inputBox">
            <input type="text" name="username">
            <label>用户名</label>
        </div>
        <div class="inputBox">
            <input type="password" name="password">
            <label>密码</label>
        </div>
        <div class="buttonBox">
            <input type="button" onclick="tologin()" value="登录">
        </div>
    </form>
</div>
</body>
</html>
