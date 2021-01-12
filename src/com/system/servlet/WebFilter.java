package com.system.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author nanfang
 * @create 2021/01/12/13:26
 * @declare 拦截器
 */
public class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("utf-8");
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //将登录状态存入session中
        HttpSession session = request.getSession();
        if(session.getAttribute("flag")==null){
            PrintWriter out = response.getWriter();
            out.print("<script type='text/javascript'>alert('非法访问，请登录！');" +
                    "location='http://localhost:8080/index.jsp'; charset='UTF-8';</script>");
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
