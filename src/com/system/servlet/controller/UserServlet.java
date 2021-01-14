package com.system.servlet.controller;


import com.system.beans.Dish;
import com.system.beans.User;
import com.system.servlet.database.DBManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author nanfang
 * @create 2021/01/11/15:25
 */
public class UserServlet extends HttpServlet {

    private final String address="http://localhost:8080/";
    HttpSession httpSession;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        //*****初始化数据库
        DBManager.getInst().initDB();
        DBManager.getInst().connectDB();
        //*****
        try {
            req.setCharacterEncoding("utf-8");
            String method = req.getParameter("method");
            if(method!=null)
            {
                System.out.println("UserServlet访问成功");
                switch (method){
                    case "login": toLogin(req,resp);break; //用户登录
                    case "register": toReg(req,resp);break;//用户注册
                    case "load": toShowList(req,resp);break;//加载所有菜单
                    case "showdetail":toShowDetail(req,resp);break;
                    case "sort":toSortDish(req, resp);break;
                    case "order":toOrder(req, resp);break;
                    case "delete":toDelete(req, resp);break;
                    case "submit":toSubmit(req, resp);break;
                    case "selected":toShowSelected(req, resp);break;
                    case "showMyOrder":toShowSelected(req, resp);break;
                    default: System.out.println("方法访问失败");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示我的订单界面
     * 后端应该在点击前端提交的同时 把已选菜品的数据显示到我的订单中
     * 后端需提供菜品id 菜品comment 菜品price
     * @param req
     * @param res
     */
    public void toShowMyOrder(HttpServletRequest req, HttpServletResponse res) {
        httpSession=req.getSession();
        String order=req.getParameter("order");
        ArrayList<Dish> dishList=DBManager.getInst().selectDishsByOrder(order);
        if(!dishList.isEmpty()){
            httpSession.setAttribute("dishes",dishList);
        }else{
            httpSession.setAttribute("dishes","");
        }
    }


    /**
     * 显示所选菜单
     * 前端从后端（已点菜单表格）读取订单号、菜品信息、已选件数、总金额 后端才装载已选列表时 应计算已点件数及总额
     * @param req
     * @param res
     */
    //有疑问，直接前端点击提交，只需要给我一个订单号
    public void toShowSelected(HttpServletRequest req, HttpServletResponse res) {
        httpSession=req.getSession();
        String orderId=req.getParameter("orderId");
        try {
            ArrayList<Dish>dishList=DBManager.getInst().selectOrderDetailByOrderId(orderId);
            if(!dishList.isEmpty()){
                httpSession.setAttribute("dishes","");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 提交订单操作
     * 前端向后端提供订单号
     * @param req
     * @param res
     */
    //不理解
    public void toSubmit(HttpServletRequest req, HttpServletResponse res) {
        httpSession=req.getSession();
        String orderId=req.getParameter("orderId");
        try {
            ArrayList<Dish>dishList=DBManager.getInst().selectOrderDetailByOrderId(orderId);
            if(!dishList.isEmpty()){
                httpSession.setAttribute("dishes","");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 已选订单中删除菜的操作
     * 前端向后端提供订单号 菜品号
     * 删除tb_order_detail中的数据
     * @param req
     * @param res
     */
    public void toDelete(HttpServletRequest req, HttpServletResponse res) {
        httpSession=req.getSession();
        String orderId=req.getParameter("orderId");
        String dishId=req.getParameter("dishId");
        try {
            if(DBManager.getInst().deleteOrderDetai(orderId,dishId)){
                ArrayList<Dish> dishList=DBManager.getInst().selectOrderDetailByOrderId(orderId);
                httpSession.setAttribute("dishes",dishList);
                res.getWriter().print("true");
            }else{
                res.getWriter().print("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //有疑问
    /**
     * 点单操作
     * 前端向后端提供订单号 菜品号
     * @param req
     * @param res
     */
    public void toOrder(HttpServletRequest req, HttpServletResponse res) {
        httpSession=req.getSession();
        String orderId=req.getParameter("orderId");
        String dishId=req.getParameter("dishId");
        //根据订单号，计算该订单所有菜的总价钱，然后放入session中供前端取数据
        try {
            if(DBManager.getInst().insertOrderDetail(orderId,dishId)){
                int consume=DBManager.getInst().selectMoneyByOrderId();
                httpSession.setAttribute("consume",consume);
                res.getWriter().print("true");
            }else{
                res.getWriter().print("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 菜品分类,根据菜品类型返回同类型所有菜的信息
     * @param req
     * @param res
     */
    public void toSortDish(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("toSortDish");
        String type = req.getParameter("type");
        httpSession=req.getSession();
        //*******************测试
        switch (type)
        {
            case "1":
                System.out.println("凉菜");break;
            case "2":
                System.out.println("热炒");break;
            case "3":
                System.out.println("汤");break;
            case "4":
                System.out.println("饮品");break;
            case  "5":
                System.out.println("主食");break;
            default:
                System.out.println("全部");break;

        }
        //***************
        //根据传来的type查找数据，将所有信息显示
        ArrayList<Dish> dishes = DBManager.getInst().selectDishByType();
        if(!dishes.isEmpty()){
            httpSession.setAttribute("dishes",dishes);
        }else{
            httpSession.setAttribute("dishes","");
        }
        try {
            res.sendRedirect(address+"jsp/list.jsp");
            res.setContentType("text/html;charset=utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据前端给出的菜品id，传给这个菜品的所有信息
     * @param req
     * @param res
     */
    public void toShowDetail(HttpServletRequest req, HttpServletResponse res) {
        httpSession=req.getSession();
        System.out.println("toShowDetail");
        Dish dish=DBManager.getInst().selectDishById(req.getParameter("id"));
        if(dish!=null){
            httpSession.setAttribute("dishes",dish);
        }else{
            httpSession.setAttribute("dishes","");
        }
        try {
            res.sendRedirect(address+"jsp/detail.jsp");
            res.setContentType("text/html;charset=utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * index.jsp中显示所有菜需要数据库中菜品信息以及订单号,后端通过setAttribute向前端写入菜品信息 订单号
     * httpSession.setAttribute("dishes",dishList); 存入数据至dishes中
     * @param req
     * @param res
     */
    public void toShowList(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("toShowList");
        httpSession=req.getSession();
        ArrayList<Dish> dishList=DBManager.getInst().selectAllDishes();
        if(!dishList.isEmpty()){
            httpSession.setAttribute("dishes",dishList);
        }else{
            httpSession.setAttribute("dishes","");
        }
        try {
            res.sendRedirect(address+"jsp/index.jsp");
            res.setContentType("text/html;charset=utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 注册用户
     * @param req 获取前端提供 用户名，密码，电话，money=0(初始用户为零)
     * @param resp 返回true或false
     */
    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
        User user=new User();
        System.out.println("toReg");
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setTel(req.getParameter("tel"));
        user.setMoney(0);
        try {
            if(DBManager.getInst().insertUser(user)) {
                resp.getWriter().print("true");
            }else resp.getWriter().print("false");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 用户登录操作
     * @param req
     * @param resp  返回给前端true或false
     */
    private void toLogin(HttpServletRequest req, HttpServletResponse resp) {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //****
        User user=DBManager.getInst().selectUserByUsername(username);
        try {
            if(user!=null&&user.getPassword().equals(password)){
                resp.getWriter().print("true");
            } else{
                resp.getWriter().print("false");
            }
        }catch (IOException e) {
            System.out.println("loginService中response错误");
            e.printStackTrace();
        }
    }
    //    /*
//    * 更新账户余额
//    * */
//    private void toPay(HttpServletRequest req, HttpServletResponse resp) {
//        user.setUsername(req.getParameter("username"));
//        int consume=Integer.parseInt(req.getParameter("consume"));//得到今日消费总钱数
//        AllService.getInst().updateUserService(req,resp,user,consume);
//    }
//
//    /*
//    * 根据用户名查找单个用户数据
//    * */
//    private void toSearch(HttpServletRequest req, HttpServletResponse resp) {
//        user.setUsername(req.getParameter("username"));
//        AllService.getInst().selectUserService(req,resp,user);
//    }
//    /*
//    * 查找所有用户数据
//    * */
//    private void toShow(HttpServletRequest req, HttpServletResponse resp) {
//        AllService.getInst().selectUserAllService(req,resp,user);
//    }

}
