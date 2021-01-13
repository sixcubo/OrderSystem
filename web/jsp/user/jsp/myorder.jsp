<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>我的订单</title>
	<link rel="stylesheet" type="text/css" href="../css/style2.css">
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
            <li class="nLi ">
              <h3 class="d"><a href="order-statistic.jsp">订单统计</a></h3>
            </li>
        </ul>
    </div>
   </div>

   <div class="myorder">
     <h1 class="m" style="color: #ff7575">我的订单</h1>
   </div>

   <div class="order" style="display: none;">
       <ul >
           <li id="回锅肉" class="orderli">
               <img src="../../../images/huiguorou.jpg" width="150" height="110" />
               <span class="food">回锅肉</span>
               <span class="num">数量：1</span>
               <span class="disc">口味偏重，由猪后臀肉、青椒、蒜苗翻炒而成。</span>
               <span class="price">18元</span>
               <button class="del">删除</button>
           </li>
           <li id="青椒玉米">
               <img src="../../../images/qingjiaoyumi.jpg" width="150" height="110" />
               <span class="food">青椒玉米</span>
               <span class="num">数量：1</span>
               <span class="disc">口味偏淡，清新素食家常菜。</span>
               <span class="price">10元</span>
               <button class="del">删除</button>
           </li>
           <li id="担担面">
               <img src="../../../images/dandanmian.jpg" width="150" height="110" />
               <span class="food">担担面</span>
               <span class="num">数量：1</span>
               <span class="disc">咸鲜微辣，特色面食小吃，面条细薄，卤汁醇香。</span>
               <span class="price">9元</span>
               <button class="del">删除</button>
           </li>
           <li id="家常荤素套餐">
               <img src="../../../images/jiachang.jpg" width="150" height="110" />
               <span class="food">家常荤素套餐</span>
               <span class="num">数量：1</span>
               <span class="disc">微辣、由猪肉、花菜等家常菜精心烹饪。</span>
               <span class="price">14元</span>
               <button class="del">删除</button>
           </li>
           <li id="剁椒鱼头">
               <img src="../../../images/duojiaoyutou.jpg" width="150" height="110" />
               <span class="food">剁椒鱼头</span>
               <span class="num">数量：1</span>
               <span class="disc">较辣、由新鲜鲤鱼做成。</span>
               <span class="price">88元</span>
               <button class="del">删除</button>
           </li>
           <li id="红烧肉炒菜">
               <img src="../../../images/hongshaochaocai.jpg" width="150" height="110" />
               <span class="food">红烧肉炒菜</span>
               <span class="num">数量：1</span>
               <span class="disc">不辣、由红烧肉和特制酱料烹饪。</span>
               <span class="price">15元</span>
               <button class="del">删除</button>
           </li>
           <li id="鱼香肉丝">
               <img src="../../../images/yuxiangrousi.jpg" width="150" height="110" />
               <span class="food">鱼香肉丝</span>
               <span class="num">数量：1</span>
               <span class="disc">酸辣兼备、口感甚佳。</span>
               <span class="price">18元</span>
               <button class="del">删除</button>
           </li>
           <li id="特色水饺">
               <img src="../../../images/shuijiao.jpg" width="150" height="110" />
               <span class="food">特色水饺</span>
               <span class="num">数量：1</span>
               <span class="disc">口味：韭菜鸡蛋、猪肉洋葱。</span>
               <span class="price">11元</span>
               <button class="del">删除</button>
           </li>
           <li id="回锅肉炒菜">
               <img src="../../../images/huiguochaocai.jpg" width="150" height="110" />
               <span class="food">回锅肉炒菜</span>
               <span class="num">数量：1</span>
               <span class="disc">微辣、回锅肉和小油菜。</span>
               <span class="price">13元</span>
               <button class="del">删除</button>
           </li>
       </ul>
   </div>

  <div class="order_content" id="first">

   </div>
   <div class="b_add">
       <a href="index.jsp"><button class="add">添加菜品</button></a>
   </div>
   <section class="my_model">
    <p class="title">删除菜品<span class="closeModel">X</span></p>
    <p>您确认要删除该菜品吗？</p>
    <div class="opBtn">
        <a href="javascript:;" class="dialog-sure">确定</a>
        <a href="javascript:;" class="dialog-close">关闭</a>
    </div>
   </section>  

   <script type="text/javascript" src="../js/jQuery.js"></script>
   <script type="text/javascript" src="../../../js/jquery.dialog.js"></script>
   
   <script type="text/javascript">
     var db = openDatabase('dishes', '1.0', '点餐数据库', 30*1024*1024);
     var datatable = document.getElementById("first");
     var dishnames=new Array();

     showAllData(datatable);
     function showData(row,datatable,i) {
        console.log(dishnames.indexOf(row.dishname));
    if(dishnames.indexOf(row.dishname)!=-1)
   {

       console.log("repeated");
   }
    else{ dishnames.push(row.dishname)
   
    var ul=document.createElement("ul");
    ul.className="order_lists";
    var li1 = document.getElementById(row.dishname.replace(/(^\s+)|(\s+$)/g,""));
    ul.appendChild(li1);
    datatable.appendChild(ul);
    console.log(datatable)
    }
    }
    function showAllData(datatable) {
    db.transaction(function(tx) {
        tx.executeSql("CREATE TABLE IF NOT EXISTS Dingdan(picname TEXT,dishname TEXT,dishdisc TEXT,disprice TEXT,dishnum TEXT)", []);
        tx.executeSql("SELECT * FROM Dingdan  ", [], function(tx, rs) {
            
            for(var i = 0; i < rs.rows.length; i++) {
                showData(rs.rows.item(i),datatable,i)
            }
        })
    })
    }

    var $order_lists = null;
    var $order_content = '';
    var namer;
    
    $('.del').click(function () {
        $order_lists = $(this).parents('.order_lists');
        $order_content = $order_lists.parents('.order_content');
        namer= $(this).parents('.order_lists').find('.food').html();
       // name=namer.split(">")[1].split("<")[0]
        console.log(name.length)
        
        $('.model_bg').fadeIn(300);
        $('.my_model').fadeIn(300);
    });
   

    //关闭模态框
    $('.closeModel').click(function () {
        closeM();
    });
    $('.dialog-close').click(function () {
        closeM();
    });
    function closeM() {
        $('.model_bg').fadeOut(300);
        $('.my_model').fadeOut(300);
    }
    //确定按钮，移除商品
    $('.dialog-sure').click(function () {
        $order_lists.remove();
        if($order_content.html().trim() == null || $order_content.html().trim().length == 0){
            //$.order_content.parents('.cartBox').remove();
            $.sendMsg('订单里空空如也!', 1000, function() {
                console.log('sendMsg closed');
            });
        }
        closeM();
        //$sonCheckBox = $('.son_check');
        //totalMoney();
        var db = openDatabase('dishes', '1.0', '点餐数据库', 30*1024*1024);
        Deletedish();
        
        function Deletedish(datatable) {
            db.transaction(function(tx) {
                tx.executeSql("CREATE TABLE IF NOT EXISTS Dingdan(picname TEXT,dishname TEXT,dishdisc TEXT,disprice TEXT,dishnum TEXT)", []);
                tx.executeSql("DELETE FROM Dingdan WHERE dishname=(?)", [namer], function(tx, rs) {
                    
                   console.log("delete success!")
                })
            })
        }
    })
   </script>
</body>