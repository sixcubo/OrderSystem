package com.system.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author cxh
 * @create 2021/01/11/15:25
 */
public class UserServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
            String method = req.getParameter("method");
            if(method!=null)
            {
                System.out.println("连接成功");
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

    }

    private void toShow(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toLogin(HttpServletRequest req, HttpServletResponse res) {
    }
}
