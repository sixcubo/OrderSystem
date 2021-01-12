package com.system.servlet.controller;

/**
 * @author cxh
 * @create 2021/01/11/15:25
 */

import com.system.beans.Merchant;
import com.system.servlet.database.DBManager;
import com.system.servlet.service.MerchantService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MerchantServlet extends HttpServlet {

    private static Merchant merchant=new Merchant();
    private static MerchantService merchantService=new MerchantService();

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("initMerchantServlet");
        DBManager.getInst().initDB();;
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
                }else if (method.equals("reg")) {
                    toReg(req,resp);
                }else if(method.equals("list")){
                    toShow(req,resp);
                }else if(method.equals("update")) {
                    toUpdate(req,resp);
                }else if(method.equals("add")){
                    toAdd(req,resp);
                }else if(method.equals("del")){
                    toDel(req,resp);
                }else if(method.equals("search")){
                    toSearch(req,resp);
                }else {
                    resp.getWriter().print("调用方法错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void toSearch(HttpServletRequest req, HttpServletResponse resp) {
        merchant.setUsername(req.getParameter("name"));
        try {
            resp.getWriter().print(merchantService.selectMerchantService(merchant));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toDel(HttpServletRequest req, HttpServletResponse resp) {
        merchant.setUsername(req.getParameter("name"));
        try {
            resp.getWriter().print(merchantService.deleteMerchantService(merchant));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toUpdate(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toShow(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toLogin(HttpServletRequest req, HttpServletResponse res) {


        // set
    }

    @Override
    public void destroy() {
        super.destroy();

        DBManager.getInst().closeDB();
    }
}
