package com.system.servlet.controller;

/**
 * @author cxh
 * @create 2021/01/11/15:25
 */

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

public class ManagerServlet extends HttpServlet {
    @java.lang.Override
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
                }else if(method.equals("upadate")) {
                    toUpdate(req,resp);
                }else if(method.equals("add")){
                    toAdd(req,resp);
                }else if(method.equals("del")){
                    toDel(req,resp);
                }else if(method.equals("search")){
                    toSearch(req,resp);
                }


            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void toSearch(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void toDel(HttpServletRequest req, HttpServletResponse resp) {
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
    }
}
