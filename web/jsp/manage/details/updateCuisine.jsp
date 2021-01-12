<%--
  Created by IntelliJ IDEA.
  User: SCurry
  Date: 2021/1/12
  Time: 9:42
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
<body>


<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">


            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/>  更新菜系



        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="#" method="post">

        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="style/images/item_point.gif"> 菜系信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="80px">菜系名称</td>
                            <td>
                                <input type="text" name="name" class="InputStyle" value="粤菜"/> *
                                <input type="hidden" name="cid" value="1" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">


            <input type="submit" value="修改" class="FunctionButtonInput">



            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>

</div>




</body>
</html>

