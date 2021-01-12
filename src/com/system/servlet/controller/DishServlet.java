package com.system.servlet.controller;

import com.system.servlet.PictureProcess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nanfang
 * @create 2021/01/12/13:21
 */
public class DishServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DishServlet");
        //PictureProcess.upload(req,resp);
        PictureProcess.deletePicture(req, resp);
    }
}
