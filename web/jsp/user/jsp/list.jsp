<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/12 0012
  Time: 上午 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--导入jstl标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--绝对路径--%>
<%
    String path = request.getContextPath();
%>

<%--<%=request.getAttribute("loginFlag")%>--%>

<%--引入jquery控件--%>
<script src="<%=path%>/js/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="<%=path%>/css/css.css">

<head>
    <title>菜单界面</title>


</head>





<script type="text/javascript">
    function myFunction() {
        var input, filter, ul, li, a, i;
        // 获得输入内容
        input = document.getElementById("mySearch");
        // 把输入的字母全部更改为大写
        filter = input.value.toUpperCase();
        // 获得导航菜单
        ul = document.getElementById("myMenu");
        // 根据标签名称获得 li 标签
        li = ul.getElementsByTagName("li");
        // 遍历搜索分类
        for (i = 0; i < li.length; i++) {
            // 在li 标签的基础上根据标签名称获得 a 标签
            a = li[i].getElementsByTagName("a")[0];
            // 导航 a 标签中的字符串是否包含 filter 子串
            if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                li[i].style.display = "";
            } else {
                li[i].style.display = "none";
            }
        }
    }


    function backImage(){
        document.body.style.backgroundImage="URL(F:\\picture\\111.jpg)";
        document.body.style.backgroundPosition="left";
        document.body.style.backgroundRepeat="no-repeat";
        document.body.style.backgroundAttachment="fixed";
    }
</script>

<body onload="backImage();">

<div class="row">
    <div class="left" style="background-color:#bbb;" >
        <h2>菜单</h2>
        <input type="text" id="mySearch" onkeyup="myFunction()" placeholder="搜索.." title="输入分类">
        <ul id="myMenu">
            <li><a href="#">川菜类</a></li>
            <li><a href="#">鲁菜类</a></li>
            <li><a href="#">苏菜类</a></li>
            <li><a href="#">粤菜类</a></li>
            <li><a href="#">浙菜类</a></li>
            <li><a href="#">闽菜类</a></li>
            <li><a href="#">徽菜类</a></li>
            <li><a href="#">湘菜类</a></li>
            <li><a href="#">京菜类</a></li>
            <li><a href="#">鄂菜类</a></li>
            <li><a href="#">凉拌海蜇皮</a></li>
            <li><a href="#">谢谢您</a></li>
        </ul>
    </div>

    <div class="right" style="background-color:#ddd;">
        <h2>内容</h2>
        <p>在搜索框输入菜单列表的分类名搜索对应内容。</p>
        <p>Some text..Some text..Some text..Some text..Some text..Some text..Some text..Some text..</p>
        <p>Some other text..Some text..Some text..Some text..Some text..Some text..Some text..Some text..</p>
        <p>Some text..</p>
    </div>
</body>
