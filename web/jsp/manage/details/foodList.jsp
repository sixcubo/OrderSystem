<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/12
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入jstl相关标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>菜品列表</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../../../jquery/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../../../jquery/page_common.js"></script>
    <link href="../../../css/manage_win/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../../../css/manage_win/css/index_1.css" />
</head>

    <script>

        //查询函数，将搜索框内容提交给后端，刷新页面
        function tosearch(){
            alert("搜索");
            $.ajax({
                url : "http://127.0.0.1:8080/test?method=searchfood",

                type: "post",

                data:{
                    name : $("[name='name']").val(),
                },

                success : function (obj){
                    alert(obj);
                    if(obj){
                        location.reload();
                    }else{
                        alert("查询错误请重试！")
                    }
                },

                dataType : "json"
            })
        }

        //删除函数，将要下架的商品名提交给后端，刷新页面
        function todelete(name){

        }

        //全选函数
        $(function (){
            $("#AllorNoneSelcet").click(function (){
                $(".ckbox").each(function (){
                    this.checked=!this.checked;
                })
            })
        })

        //批量下架函数，将要批量下架的函数封装成数组，提供给后端，刷新页面
        function massDelecte(){
            var num=0;
            $("[name='ckbox']:checked").each(function (){
                num=num+1;
            })
            if(num==0){
                alert("请至少选择一个商品");
            }else{
                if(confirm("您确定全部下架吗？")) {
                    var data=[];
                    $("[name='ckbox']:checked").each(function (){
                        var row = $(this).parents("tr")
                        var name  = row.find($("[name='name']")).text();
                        data.push(name);
                    })
                    alert(data)

                    $.ajax({

                        url:"http://127.0.0.1:8080/test?method=massdelete",

                        type:"post",

                        data:{
                            data:JSON.stringify(data)
                        },

                        success:function (obj){
                            if(obj){
                                alert("删除成功！")
                                location.reload();
                            }else{
                                alert("删除失败！")
                            }
                        },

                        dataType : "json"
                    })
                }
            }
        }
    </script>


<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 菜品列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>


<!-- 搜索组件 -->
<div id="QueryArea">
    <form >
        <input type="text" name="name" title="请输入菜品名称">
        <input type="button" value="搜索" onclick="tosearch()">
    </form>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">

    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>全选/全不选<input type="checkbox" id="AllorNoneSelcet"></td>
            <td>菜名</td>
            <td>所属菜系</td>
            <td>价格</td>
            <td>会员价格</td>
            <td>操作</td>
        </tr>
        </thead>

        <!--显示数据列表 -->
        <tbody id="TableData">
        <c:forEach items="${foods}" var="food">
            <tr class="TableDetail1">
                <td><input type="checkbox" class="ckbox" name="ckbox"></td>
                <td name="name">${food.name}</td>
                <td name="series">${food.series}</td>
                <td name="normal_price">${food.normal_price}</td>
                <td name="vip_price">${food.vip_price}</td>
                <td>
                    <a href="updateFood.jsp"  class="FunctionButton">更新</a>
                    <a href = "javascript:void(0);" class="FunctionButton" onclick ="todelete(${food.name})">下架</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
        <div class="FunctionButton"><a href="saveFood.jsp">添加</a></div>
        <div class="FunctionButton"><a href = "javascript:void(0);"
                                       onclick ="massDelecte()">批量下架</a></div>
    </div>
</div>
</body>
</html>
