<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/12
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>

    <title>商品种类管理</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../../../jquery/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../../../jquery/page_common.js"></script>
    <link href="../../../css/manage_win/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="style/css/index_1.css" />
</head>

<script>

    function tosearch(){
        alert("search")
    }

    function todelete(){
        alert("delete")
    }

    function toupdate(){
        alert("update")
    }



</script>

<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="../../../css/manage_win/images/title_arrow.png" /> 商品种类列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>
<!-- 过滤条件 -->
<div id="QueryArea">
    <form>
        <input type="hidden" name="method" value="search">
        <input type="text" name="keyword" title="请输入商品种类">
        <input type="button" value="搜索" onclick="tosearch()">
    </form>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>商品种类</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">

        <tr>
            <td>川菜</td>
            <td>
                <a href = "javascript:void(0);" class="FunctionButton" onclick ="toupdate()">更新</a>
                <a href = "javascript:void(0);" class="FunctionButton" onclick ="todelete()">下架</a>
            </td>
        </tr>

        </tbody>
    </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
        <div class="FunctionButton">
            <a href="saveCuisine.jsp">添加</a>
        </div>
    </div>
</div>
</body>
</html>