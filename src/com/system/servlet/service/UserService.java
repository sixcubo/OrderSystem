package com.system.servlet.service;
import com.system.beans.User;
import com.system.servlet.database.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author nanfang
 * @create 2021/01/11/14:43
 */

public class UserService  {
    private User user;
    private HttpSession httpSession;
    /*
    * 用户登录,判断密码是否相等
    * */
    public void loginService(HttpServletRequest request, HttpServletResponse response,User user){
        String password=user.getPassword();
        //****测试使用
        System.out.println(user);
        DBManager.getInst().initDB();
        DBManager.getInst().connectDB();
        //****
        this.user=DBManager.getInst().selectUserByUsername(user.getUsername());
        try {
            if(this.user!=null&&password.equals(this.user.getPassword())){
                response.getWriter().print("true");
            } else{
                response.getWriter().print("false");
            }
        } catch (IOException e) {
            System.out.println("loginService中response错误");
            e.printStackTrace();
        }

    }
    /*
    * 用户注册
    * */
    public void registerService(HttpServletRequest request, HttpServletResponse response,User user){
        try {
            response.getWriter().print("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 删除用户
    * */
    public void deleteUserService(HttpServletRequest request, HttpServletResponse response,User user) {
    }
    /*
    *查找单个用户
    */
    public void selectUserService(HttpServletRequest request, HttpServletResponse response,User user) {
    }
    /*
    * 查找所有用户
    * */
    public void selectUserAllService(HttpServletRequest request, HttpServletResponse response,User user){
    }
    /*
    * 更新用户信息，账户余额
    * */
    public String updateUserService(HttpServletRequest request, HttpServletResponse response,User user,int consume){
        return null;
    }
}
