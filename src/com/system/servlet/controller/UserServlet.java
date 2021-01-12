package com.system.servlet.controller;

import com.system.beans.Users;
import com.system.servlet.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cxh
 * @create 2021/01/11/15:25
 */
public class UserServlet extends HttpServlet {
    private static Users user=new Users();
    private static UserService userService=new UserService();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
            String method = req.getParameter("method");
            if(method!=null)
            {
                System.out.println("访问后端成功");
                if(method.equals("login")){
                    toLogin(req,resp);
                }else if (method.equals("reg")) {
                    toReg(req,resp);
                }else if(method.equals("list")){
                    toShow(req,resp);
                }else if(method.equals("search")){
                    toSearch(req,resp);
                }else if(method.equals("pay")){
                    toPay(req,resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toPay(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toSearch(HttpServletRequest req, HttpServletResponse resp) {
        user.setAccount(req.getParameter("account"));
        try {
            resp.getWriter().print(userService.selectUserService(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void toShow(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toLogin(HttpServletRequest req, HttpServletResponse res) {
    }
}
