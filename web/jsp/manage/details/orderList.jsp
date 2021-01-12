<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/12
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>商品管理</title>



    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../../../jquery/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="../../../jquery/page_common.js"></script>
    <link href="../../../css/manage_win/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../../../css/manage_win/css/index_1.css.css" />
    <script type="text/javascript">
        setInterval(function(){
            window.location.href = "/wirelessplatform/client.html?method=list";
        },1000 * 50);
    </script>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="style/images/title_arrow.gif" /> 餐厅订单列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>订单编号</td>
            <td>餐桌名</td>
            <td>下单日期</td>
            <td>总金额</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">

        <tr height="60">
            <td>15375222</td>
            <td>纽约</td>
            <td>2014-12-08 23:29:18.0</td>
            <td>204.0</td>

            <td>未结账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

            <td>
                <a href="orderDetail.html" class="FunctionButton">详细</a>

                <a href="#" class="FunctionButton">结账</a>
            </td>
        </tr>

        </tbody>
    </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail" align="center">
    </div>
</div>
</body>
</html>

