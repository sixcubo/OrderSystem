<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/11
  Time: 14:16
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
    <title>用户注册界面</title>
    <link rel="stylesheet" href="../../css/customer_login/customer_login.css">
</head>
<%--导入jquery--%>
<script src="<%=contextPath%>/jquery/jquery-1.7.2.js"></script>
<script>

    function toregister(){
        //非空检验&两次密码一致性检验
        if($("[name='username']").val()=='' || $("[name='password']").val()==''
            || $("[name='tel']").val()==''){
            if($("[name='username']").val()==''){
                alert("用户名不能为空哦！")
                return ;
            }else if($("[name='password']").val()==''){
                alert("请设置一个不会忘的密码哦！")
            }else if($("[name='password']").val()==''){
                alert("请确认一次密码!");
                return ;
            }else if($("[name='password']").val()!=$("[name='password2']").val()){
                alert("两次密码不一致，请重新输入！")
                return ;
            } else if($("[name='tel']").val()==''){
                alert("留下一个电话，方便我们联系您哦！")
            }
        }else{
            $.ajax({
                url : "http://127.0.0.1:8080/user?method=register",
                type : "post",
                data : {
                    username : $("[name='username']").val(),
                    password : $("[name='password']").val(),
                    tel : $("[name='tel']").val(),
                },
                success:function (obj)
                {
                    if(obj){
                        alert("注册成功！")
                        location.href="../login/customer_login.jsp";
                    }else{
                        alert("登录失败，请重试，或联系管理员！")
                    }
                },

                dataType : "json"
            })
        }
    }

    function goback(){
        location.href="../login/customer_login.jsp"
    }
</script>

<body>
<div class="box">
    <h2>欢迎您成为新用户</h2>
    <form>
        <div class="inputBox">
            <input type="text" name="username">
            <label>用户名</label>
        </div>
        <div class="inputBox">
            <input type="password" name="password">
            <label>密码</label>
        </div>
        <div class="inputBox">
            <input type="password" name="password2">
            <label>确认密码</label>
        </div>
        <div class="inputBox">
            <input type="text" name="tel">
            <label>电话号码</label>
        </div>
        <div class="inputBox">
            <input type="text" name="email">
            <label>email</label>
        </div>
        <div class="buttonBox">
            <input type="button" value="注册" onclick="toregister()">&nbsp&nbsp
            <input type="button" value="返回" onclick="goback()">
        </div>
    </form>
</div>
</body>
</html>
