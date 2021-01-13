package com.system.servlet.controller;


import com.system.beans.User;
import com.system.servlet.service.AllService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nanfang
 * @create 2021/01/11/15:25
 */
public class UserServlet extends HttpServlet {
    private static User user=new User();
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
                }else if (method.equals("register")) {
                    toReg(req,resp);
                }else if(method.equals("list")){
                    toShow(req,resp);
                }else if(method.equals("search")){
                    toSearch(req,resp);
                }else if(method.equals("pay")){
                    toPay(req,resp);
                }else{
                    resp.getWriter().print("方法访问失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    *
    * */
    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("toReg");
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setTel(req.getParameter("tel"));
        AllService.getInst().registerService(req,resp,user);
    }

    /*
    * 更新账户余额
    * */
    private void toPay(HttpServletRequest req, HttpServletResponse resp) {
        user.setUsername(req.getParameter("username"));
        int consume=Integer.parseInt(req.getParameter("consume"));//得到今日消费总钱数
        AllService.getInst().updateUserService(req,resp,user,consume);
    }

    /*
    * 根据用户名查找单个用户数据
    * */
    private void toSearch(HttpServletRequest req, HttpServletResponse resp) {
        user.setUsername(req.getParameter("username"));
        AllService.getInst().selectUserService(req,resp,user);
    }
    /*
    * 查找所有用户数据
    * */
    private void toShow(HttpServletRequest req, HttpServletResponse resp) {
        AllService.getInst().selectUserAllService(req,resp,user);
    }
    private void toLogin(HttpServletRequest req, HttpServletResponse resp) {
        //username 为前端传送过来的用户名数据 password 为密码
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        AllService.getInst().loginService(req,resp,user);
        // TODO: 连接数据库
    }
}
