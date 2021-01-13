<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>已选菜品</title>
    <link href="../css/style1.css"  rel="stylesheet"  media="screen"  type="text/css">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/carts.css">
    <script src="../../../js/jquery.min.js"></script>
    <script src="../../../js/jquery.dialog.js"></script>
    <script src="../../../js/carts.js"></script>
    <style type="text/css">a {text-decoration: none}</style>
</head>

<script>

    //删除菜品方法
    function toDelete(){

        var flag = confirm("是否确认删除?")
        if (flag){
            alert("删除成功");
            // location.href = "http://localhost:8080/user?method=selete";
        }
        else{
            alert("删除失败");
        }
    }

    //提交结账方法
    function toSubmit(){
        var flag = confirm("是否确认结账?");
        if (flag){
            $.ajax({

                url:"http://localhost:8080/user?method=submit",

                data: {
                    <%--order_id:${order.id}--%>
                },

                success:function (obj){

                    if(obj){

                        alert("下单成功")
                        location.href = "http://localhost:8080/user?method=load"
                    }
                }
            })
        }
    }
</script>

<div>
<div  class="nav_bg">
    <div class="wrap"> 
        <ul id="nav" class="nav clearfix">
            <li class="nLi" style="font-size: 30px;">
                <h3 class="e"><a  href="#" style="font-size: 23px;">饿了呀</a></h3>
            </li>
            <li class="nLi">
              <h3 class="d"><a  href="index.jsp">首页</a></h3>
            </li>
            <li class="nLi ">
              <h3 class="d"><a href="myorder.jsp">我的订单</a></h3>
            </li>

            <li class="nLi ">
              <h3 class="d"><a href="order-statistic.jsp">我的账户</a></h3>
            </li>
        </ul>
    </div>
</div>
<div class="left" >
    <ul class="leftborder">
        <li class="border1">
            <a href="myorder.jsp">
                <img src='../../../images/dingdan.png' width=14 height=14 />
                <span class="outer">  我的订单</span>
                <br>
                <br>
            </a>
            <span class="inner">近三个月的订单</span>
        </li>
        <li class="border2">
            <a href="index.jsp">
                <img src='../../../images/remaicaipin.png' width=14 height=14 />
                <span class="outer">所有菜品</span>
            </a>
        </li>
        <li class="border3">
            <a href="list.jsp">
                <img src='../../../images/tuijian.png' width=14 height=14 />
                <span class="outer">菜单分类</span>
            </a>
        </li>
        <li class="border4">
            <a href="">
                <img src='../../../images/menu.png' width=14 height=14 />
                <span class="outer">热卖菜品</span>
            </a>
        </li>
        <li class="border5clicked">
                <a href="./selected.jsp">
                    <img src='../../../images/today.png' width=14 height=14 />
                    <span class="outer">已选菜品</span>
                </a>
        </li>
    </ul>

    <p style="font-size: 16px;  position: absolute; top:8.9%; left:48.5%;text-align: center;"><b>已点菜单</b></p>
    <br>
    <br>
    <div class="projects">
        <!-- <br>
        <p style="font-size: 15px; height: 30px;width:300px;background-color: silver;padding: 0 0 0 20px;text-align: center;" >今日菜品</p> -->
        <ul class="rightborder">
            <!-- <p style="font-size: 16px;  background-color: rgb(251, 255, 0);text-align: center;">当天菜品</p> -->
            <li class="instruct">
                <span style="padding:0px 65px 0 40px;position: relative ;top:25%;"><b>菜品样式</b> </span>
                <span style="padding:0px 100px 0 0;position: relative ;top:25%;"><b>菜品名称</b> </span>
                <span style="padding:0px 110px 0 0;position: relative ;top:25%;"><b>菜品简介</b> </span>
                <span style="padding:0px 70px 0 30px;position: relative ;top:25%;"><b>菜品价格（单点）</b> </span>
                <span style="padding:0px 100px 0 0;position: relative ;top:25%;"><b>菜品评价</b> </span>
                <span style="padding:0px 0px 0 0;position: relative ;top:25%;"><b>操作</b></span>
            </li>
            <div class="cont">
                <c:forEach items="${dishes}" var="dies">
                    <li class="dishes" id="first">
                        <img src=${dies.url} width=136px height=80px />
                        <span id="one">${dies.name} </span>
                        <span id="two"><a href="">菜品详情</a></span>
                        <span id="three">${dies.price} </span>
                        <span id="four">${dies.score}</span>
                        <span id="five">
                  <button class="inn" onclick="toDelete()">删除菜品</button>
              </span>
                    </li>
                </c:forEach>
            </div>

        </ul>
    </div>

    <!--底部-->
    <div class="bar-wrapper">
        <div class="bar-right">
            <div class="piece">已选商品<strong class="piece_num">${count}</strong>件</div>
            <div class="totalMoney">共计: <strong class="total_text">0.00</strong></div>
            <input value="提交" type="submit" onclick="toSubmit()" style="width:120px;height:50px">
        </div>
    </div>
</section>
</div>
</div>