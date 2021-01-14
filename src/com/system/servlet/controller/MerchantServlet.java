package com.system.servlet.controller;

/**
 * @author nanfang
 * @create 2021/01/11/15:25
 */

import com.system.beans.Dish;
import com.system.beans.Merchant;
import com.system.beans.Order;
import com.system.beans.Table;
import com.system.servlet.database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class MerchantServlet extends HttpServlet {
    private String address="http://127.0.0.1:8080/";
    private HttpSession httpSession;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("initMerchantServlet");
        DBManager.getInst().initDB();
        DBManager.getInst().connectDB();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp){
        try {
            DBManager.getInst().initDB();
            DBManager.getInst().connectDB();
            req.setCharacterEncoding("utf-8");
            String method = req.getParameter("method");
            if(method!=null) {
                switch (method){
                    case "login":toLogin(req,resp);break;
                    case "register":toReg(req, resp);break;
                    case "update":toUpdatePassword(req, resp);break;
                    case "add": toAdd(req, resp);break;
                    case "delete":toDel(req, resp);break;
                    //foodList相关方法
                    case "showfoodlist":;break;
                    case "searchfood":;break;
                    case "massdelete":;break;
                    case "deletefood":;break;
                    //Boardlist相关方法
                    case "showboardlist":;break;
                    case "toboardlist":;break;
                    case "addboard":;break;
                    //OrderList相关
                    case "toorderlist":;break;
                    case "searchorder":;break;
                    case "showorderlist":;break;
                    case "todetail":;break;
                    default:
                        System.out.println("方法调用错误");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //*********************footList相关方法******************

    /**
     * 从数据库中查找得到所有菜品封装为Dish，存入ArrayList<Dish> bg_foods中
     * @param req
     * @param resp 不返回数据
     */
    public void showFootList(HttpServletRequest req, HttpServletResponse resp) {
        httpSession=req.getSession();
        ArrayList<Dish>dishList=DBManager.getInst().selectAllDishes();
        if(!dishList.isEmpty()){
            httpSession.setAttribute("bg_foods",dishList);
        }else{
            httpSession.setAttribute("bg_foods","");
        }
    }

    /**
     * 从数据库中查找得到所有菜名与name有关的菜品
     * （模糊查找，如name=白,可以查出“开水煮白菜”，“白斩鸡”）封装为Dish，存入ArrayList<Dish> bg_foods中
     * @param req
     * @param resp 查找成功返回“true” , 否则“false”
     */
    public void serchFood(HttpServletRequest req, HttpServletResponse resp) {
        httpSession=req.getSession();
        ArrayList<Dish>dishList=DBManager.getInst().selectDishesByName();
        if(!dishList.isEmpty()){
            httpSession.setAttribute("bg_foods",dishList);
            resp.getWriter().print("true");
        }else{
            resp.getWriter().print("false");
        }
    }

    /**
     *  根据多个菜的id 同时删除菜品 并将数据库中剩余菜品封装为Dish存入session
     * @param req 从前端获取多个id 接收数据data（多个菜名用逗号隔开的字符串）
     * @param resp 返回true或false
     */
    public void massdelete(HttpServletRequest req, HttpServletResponse resp) {
        httpSession=req.getSession();
        String []data=req.getParameterValues("data[]");
        try {
            if(data.length>0&&DBManager.getInst().deleteDishByIds(data)){
                ArrayList<Dish>dishList=DBManager.getInst().selectAllDishes();
                httpSession.setAttribute("foods",dishList);
                resp.getWriter().print("true");
            }else{
                resp.getWriter().print("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除菜名为name的菜品后，从数据库中查找剩余菜品封装为Dish，存入ArrayList<Dish> bg_foods中
     * @param req 根据 ‘id' 得到需要删除菜的id
     * @param resp  查找成功返回“true” , 否则“false”
     */
    public void deleteFood(HttpServletRequest req, HttpServletResponse resp) {
        httpSession=req.getSession();
        int dishId=Integer.parseInt(req.getParameter("id"));
        try {
            if(DBManager.getInst().deleteDishById(dishId)){
                ArrayList<Dish> dishList=DBManager.getInst().selectAllDishes();
                resp.getWriter().print("true");
            }else{
                resp.getWriter().print("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //**************************BoardList相关方法************************
    /**
     * 查找数据库中所有状态为state餐桌，封装成为Board，存入ArrayList<Board> bg_boards中
     * @param req 根据 ‘state' 得到所有状态为state的餐桌
     * @param resp 查找成功返回“true”  , 否则返回“false”
     */
    public void showBoardList(HttpServletRequest req, HttpServletResponse resp) {
        httpSession=req.getSession();
        String state=req.getParameter("state");
        ArrayList<Table> tableList=DBManager.getInst().selectTablesByState(state);
        if(!tableList.isEmpty()){
            httpSession.setAttribute("bg_boards",tableList);
            try {
                resp.getWriter().print("true");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //疑问，通上一个函数
    public void toBoardList(HttpServletRequest req, HttpServletResponse resp) {

    }
    /**
     * 添加一个桌位
     * @param req  接收数据 boardid , maxnum（新增一桌）
     * @param resp 添加成功返回“true”  , 否则返回“false”
     */
    public void addBoard(HttpServletRequest req, HttpServletResponse resp) {
        String boardId =req.getParameter("boardid");
        String maxNum=req.getParameter("maxnum");
        if(DBManager.getInst().insertTable(boardId,maxNum,0,"空闲")){
            try {
                resp.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //*********************OrderList相关*************************

    /**
     * 查找数据库中所有状态为未完成的订单，封装成为Order，存入ArrayList<Order> bg_orders中
     * @param req 接受数据无
     * @param resp  重定向 http://127.0.0.1:8080/jsp/manage/details/orderList.jsp
     */
    public void toorDerList(HttpServletRequest req, HttpServletResponse resp) {
        httpSession=req.getSession();
        ArrayList<Order> orderList= DBManager.getInst().selectAllOrdersByState(0);
        if(!orderList.isEmpty()){
            try {
                resp.sendRedirect(address + "jsp/manage/details/orderList.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void serchOrder(HttpServletRequest req, HttpServletResponse resp) {
        httpSession=req.getSession();
        String username=req.getParameter("username");
        ArrayList<Order>orderList=DBManager.getInst().selectOrderByUsername(username);
        if(!orderList.isEmpty()){
            try {
                httpSession.setAttribute("bg_orders",orderList);
                resp.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查找数据库中状态为type的订单，封装成为Order，存入ArrayList<Order> bg_orders中
     * @param req 接受数据：type
     * @param resp 查询成功返回“true”  , 查找失败返回“false”
     */
    public void showOrderList(HttpServletRequest req, HttpServletResponse resp) {
        String type=req.getParameter("type");
        httpSession = req.getSession();
        ArrayList<Order> orders = DBManager.getInst().selectOrdersBytype(type);
        if(!orders.isEmpty()){
            httpSession.setAttribute("bg_orders", orders);
            try {
                resp.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 查找数据库中id为id的订单，封装成为Order，将此Order存入session中命名为bg_orderdetail
     * @param req 接受数据：id
     * @param resp 查询成功返回“true”  , 查找失败返回“false”
     */
    public void toDetail(HttpServletRequest req, HttpServletResponse resp) {
        String id=req.getParameter("id");
        httpSession = req.getSession();
        ArrayList<Order> orders = DBManager.getInst().selectOrdersByid(id);
        if(!orders.isEmpty()){
            httpSession.setAttribute("bg_orders", orders);
            try {
                resp.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 修改商家密码
     * @param req 获取用户名 username 和老密码 password  和新密码
     * @param resp 返回true或false
     */
    private void toUpdatePassword(HttpServletRequest req, HttpServletResponse resp) {
        String username =req.getParameter("username");
        String password=req.getParameter("password");
        String newPassword=req.getParameter("newPassword");
        if(DBManager.getInst().selectMerchantByName(username)==password){
            DBManager.getInst().updateMerchant(username,newPassword);
            try {
                resp.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                resp.getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 申请商家账号
     * @param req
     * @param resp
     */
    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
        Merchant merchant=new Merchant();
        merchant.setUsername(req.getParameter("username"));
        merchant.setPassword(req.getParameter("password"));
        try {
            if(DBManager.getInst().insertMerchat(merchant)){
                resp.getWriter().print("true");
            }else{
                resp.getWriter().print("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 前端提供用户名和密码，后端根据用户名查找密码和前端提供密码进行比对
     * @param req
     * @param res
     */
    private void toLogin(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("toLogin");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(password.equals(DBManager.getInst().selectMerchantPassword(username))){
            try {
                res.getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 销毁数据库
     */
    @Override
    public void destroy() {
        super.destroy();
        DBManager.getInst().closeDB();
    }



    /*
     * 重定向至orderList.jsp界面
     * */
    public void toOrderList(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(address + "jsp/list.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
