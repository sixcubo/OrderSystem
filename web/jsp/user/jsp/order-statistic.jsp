<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>订单统计</title>
	<link rel="stylesheet" type="text/css" href="../css/style3.css">
  <style type="text/css">ul{list-style:none} a {text-decoration: none}</style>
</head>
<body>
  <div class="nav_bg">
    <div class="wrap"> 
        <ul id="nav" class="nav clearfix">
            <li class="nLi" style="font-size: 30px;">
                <h3 class="e"><a  href="#" style="font-size: 23px;">饿了呀</a></h3>
            </li>
            <li class="nLi">
              <h3 class="d"><a  href="index.jsp">首页</a></h3>
            </li>
            <li class="nLi">
              <h3 class="d"><a href="myorder.jsp">我的订单</a></h3>
            </li>
            <li class="nLi">
              <h3 class="d"><a href="order-statistic.jsp">订单统计</a></h3>
            </li>
        </ul>
    </div>
  </div>
  <div class="statistic">
     <h1 class="m" style="color: #ff7575">订单统计</h1>
  </div>
  
  

  <div class="left" >
        <ul class="leftborder">
            <li class="selected">
                <a href="#">
                    <span class="outer">菜品统计</span> 
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="outer">价格趋势</span> 
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="outer">食材分布</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="outer">口味比重</span>
                </a>
            </li>    
        </ul>
  </div>

  <div class="content" style="width:50%">
  	<div>
  		<canvas id="popChart1" width="800" height="500">
  	</div>
  	<div class="display_none">
  		<canvas id="popChart2" width="800" height="500">
  	</div>
  	<div class="display_none">
  		<canvas id="popChart3" width="800" height="500">
  	</div>
  	<div class="display_none">
  		<canvas id="popChart4" width="800" height="500">
  	</div>
  </div>

	<script src="../../../js/jquery.js"></script>
	<script src="../../../js/Chart.js"></script>
	<script type="text/javascript">
    var popCanvas1 =document.getElementById("popChart1").getContext("2d");
    var popCanvas2 =document.getElementById("popChart2").getContext("2d");
    var popCanvas3 =document.getElementById("popChart3").getContext("2d");
    var popCanvas4 =document.getElementById("popChart4").getContext("2d");
    var barChart = new Chart(popCanvas1, {
      type: 'bar',
      data: {
        labels: ["回锅肉", "青椒玉米", "担担面"],
        datasets: [{
          label: '订购数量',
          data: [34, 27, 40],
          backgroundColor: [
          'rgba(255, 99, 132, 0.6)',
          'rgba(54, 162, 235, 0.6)',
          'rgba(255, 206, 86, 0.6)',
          'rgba(153, 102, 255, 0.6)'
          ]
        }]
      }
    });
    var pieChart = new Chart(popCanvas3, {
      type: 'pie',
      data: {
        labels:['肉类','素类','面食类'],
        datasets: [{
          label:'订购数量',
          data: [34, 27, 40],
          backgroundColor:[
          'rgba(255, 99, 132, 0.6)',
          'rgba(54, 162, 235, 0.6)',
          'rgba(255, 206, 86, 0.6)',
          'rgba(153, 102, 255, 0.6)'
          ]
        }]
      }
    });
    var lineChart = new Chart(popCanvas2, {
      type: 'line',
      data: {
        labels:['9元','10元','18元'],
        datasets: [{
          label:'订购数量',
          data: [40, 27, 34],
          backgroundColor:[
          'rgba(255, 99, 132, 0.6)',
          'rgba(54, 162, 235, 0.6)',
          'rgba(255, 206, 86, 0.6)',
          'rgba(153, 102, 255, 0.6)'
          ]
        }]
      }
    });
    var radarChart = new Chart(popCanvas4, {
      type: 'radar',
      data: {
        labels:['口味偏重','口味偏淡','口味适中'],
        datasets: [{
          label:'订购数量',
          data: [34, 27, 40],
          backgroundColor:[
          'rgba(255, 99, 132, 0.6)',
          'rgba(54, 162, 235, 0.6)',
          'rgba(255, 206, 86, 0.6)',
          'rgba(153, 102, 255, 0.6)'
          ]
        }]
      }
    });

    $(function(){
    	$(".left li").click(
    		function(){
    			var divShow = $(".content").children("div");
    			if(!$(this).hasClass("selected")){
    				var index = $(this).index();   
    				$(this).addClass("selected");            
            $(this).siblings("li").removeClass("selected");                     
            $(divShow[index]).show("fast");
            $(divShow[index]).siblings("div").hide("fast");
          }
    		}
    	)
    })
    </script>
</body>