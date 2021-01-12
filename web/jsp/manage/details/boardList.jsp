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
    <!-- 包含公共的JSP代码片段 -->

    <title>餐桌管理</title>



    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../../../jquery/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../../../jquery/page_common.js"></script>
    <link href="../../../css/manage_win/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../../../css/manage_win/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 餐桌列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="/wirelessplatform/board.html" method="get">
        <input type="hidden" name="method" value="search">
        <input type="text" name="keyword" title="请输入餐桌名称">
        <input type="submit" value="搜索">
    </form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>桌号</td>
            <td>状态</td>
            <td>预定时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">

        <tr class="TableDetail1">
            <td align="center"> 纽约&nbsp;</td>
            <td align="center">预定</td>
            <td align="center">2014-12-08 23:31:12</td>
            <td>
                <a href="/wirelessplatform/board.html?method=update&id=1&isBook=0" class="FunctionButton">退桌</a>
                <a href="/wirelessplatform/board.html?method=delete&id=1" onClick="return delConfirm();"class="FunctionButton">删除</a>
            </td>
        </tr>

        </tbody>
    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
        <div class="FunctionButton"><a href="saveBoard.html">添加</a></div>
    </div>
</div>
</body>
</html>
