package com.system.servlet.controller;

/**
 * @author nanfang
 * @create 2021/01/11/15:25
 */

import com.system.beans.Merchant;
import com.system.beans.Order;
import com.system.servlet.database.DBManager;
import com.system.servlet.service.AllService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class MerchantServlet extends HttpServlet {
    private String address="";
    private static Merchant merchant=new Merchant();
    private HttpSession httpSession;
    //private static MerchantService merchantService=new MerchantService();

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
            req.setCharacterEncoding("utf-8");
            String method = req.getParameter("method");
            if(method!=null) {
                if (method.equals("login")) {
                    toLogin(req,resp);
                }else if (method.equals("register")) {
                    toReg(req,resp);
                }else if(method.equals("update")) {
                    toUpdatePassword(req,resp);
                }else if(method.equals("add")){
                    toAdd(req,resp);
                }else if(method.equals("delete")){
                    toDel(req,resp);
                }else if(method.equals("search")){
                    toSelect(req,resp);
                }else if(method.equals("selectAll")){
                    toSelectAll(req,resp);
                }else {
                    resp.getWriter().print("调用方法错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    * 查找单个商家信息，根据商家名
    * */
    private void toSelect(HttpServletRequest req, HttpServletResponse resp) {
        merchant.setUsername(req.getParameter("name"));
        AllService.getInst().selectMerchant(req,resp,merchant);
    }

    /*
    * 根据商家名删除该商家
    * */
    private void toDel(HttpServletRequest req, HttpServletResponse resp) {
        merchant.setUsername(req.getParameter("username"));
        AllService.getInst().deleteMerchantService(req,resp,merchant);
    }
    /*
    * 添加一个商家
    * */
    private void toAdd(HttpServletRequest req, HttpServletResponse resp) {
        merchant.setUsername(req.getParameter("username"));
        merchant.setPassword(req.getParameter("password"));
        AllService.getInst().insertMerchant(req,resp,merchant);
    }

    /*
    * 修改商家密码
    * */
    private void toUpdatePassword(HttpServletRequest req, HttpServletResponse resp) {
        merchant.setUsername(req.getParameter("username"));
        merchant.setPassword(req.getParameter("password"));
        Merchant newMerchant=new Merchant();
        newMerchant.setUsername(merchant.getUsername());
        newMerchant.setPassword(req.getParameter("newPassword"));
        AllService.getInst().updateMerchantService(req,resp,merchant,newMerchant);
    }



    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
        merchant.setUsername(req.getParameter("username"));
        merchant.setPassword(req.getParameter("password"));
        AllService.getInst().registerService(req,resp,merchant);
    }

    private void toLogin(HttpServletRequest req, HttpServletResponse res) {
        merchant.setPassword(req.getParameter("password"));
        merchant.setUsername(req.getParameter("username"));
        AllService.getInst().loginService(req,res,merchant);
        // set
    }
    private void toSelectAll(HttpServletRequest req, HttpServletResponse res) {
        AllService.getInst().selectMerchantAll(req,res);
    }

    @Override
    public void destroy() {
        super.destroy();
        DBManager.getInst().closeDB();
    }

    /**
     * 向前端传输所有的订单数据
     */
    public void showBoardList(HttpServletRequest request, HttpServletResponse response) {
        httpSession = request.getSession();
        ArrayList<Order> orders = DBManager.getInst().selectAllOrders();
        httpSession.setAttribute("bg_foods", orders);
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
