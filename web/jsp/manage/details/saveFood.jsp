<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/12
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- 包含公共的JSP代码片段 -->
    <title>无线点餐平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../../../jquery/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../../../jquery/page_common.js"></script>
    <link href="../../../css/manage_win/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../../../css/manage_win/css/index_1.css" />
</head>

<script>
    function add(){
        alert("add")
        $.ajax({

            url:"http://127.0.0.1:8080/test?method=additem",

            type:"post",

            data:{
                name:$("[name='name']").val(),
                normal_price:$("[name='normal_price']").val(),
                vip_price:$("[name='vip_price']").val(),
                introduce:$("[name='introduce']").val(),
                imageUrl:$("[name='imageUrl']").val(),
            },

            success:function (obj){
                if(obj){
                    alert("添加成功")
                }else{
                    alert("无了")
                }
            },

            dataType:JSON
        })


    }
</script>

<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="../../../css/manage_win/images/title_arrow.png"/> 添加新菜品
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="#" method="post" enctype="multipart/form-data">
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="../../../css/manage_win/images/item_point.gif"> 商品信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">类型</td>
                            <td>
                                <select name="cid" style="width:80px">

                                    <option value="1">主食</option>

                                    <option value="2">饮料</option>

                                    <option value="3">甜点</option>

                                    <option value="4">火锅</option>

                                </select>
                                *<input type="hidden" name="id" value="" /></td>
                        </tr>
                        <tr>
                            <td width="80px">商品名</td>
                            <td><input type="text" name="name" class="InputStyle" value=""/> *</td>
                        </tr>
                        <tr>
                            <td>价格</td>
                            <td><input type="text" name="normal_price" class="InputStyle" value=""/> *</td>
                        </tr>
                        <tr>
                            <td>会员价格</td>
                            <td><input type="text" name="vip_price" class="InputStyle" value=""/> *</td>
                        </tr>

                        <tr>
                            <td>简介</td>
                            <td><textarea name="introduce" class="TextareaStyle"></textarea></td>
                        </tr>
                        <tr>
                            <td width="80px">菜品图片</td>
                            <td>
                                <input type="file" name="imageUrl"/> *
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="button" value="添加" class="FunctionButtonInput"  onclick="add()">
            <a href="foodList.jsp" class="FunctionButton">返回</a>
        </div>
    </form>
</div>
</body>
</html>

