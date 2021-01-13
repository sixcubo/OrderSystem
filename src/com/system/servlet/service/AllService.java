package com.system.servlet.service;

import com.system.beans.Dish;
import com.system.beans.Merchant;
import com.system.beans.User;
import com.system.servlet.database.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author nanfang
 * @create 2021/01/13/16:52
 */
public class AllService {
    private User user;
    private HttpSession httpSession;
    private static AllService instance = new AllService(); // 单例

    public static AllService getInst() {
        return instance;
    } // 获取单例

    //****************************************与用户相关操作***********************************
    /*
     * 用户登录,判断密码是否相等
     * */
    public void loginService(HttpServletRequest request, HttpServletResponse response, User user){
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
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
        }catch (IOException e) {
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


    //****************************************与商家相关操作***********************************
    public void loginService(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
        //****测试使用
        System.out.println(merchant);
        DBManager.getInst().initDB();
        DBManager.getInst().connectDB();
        //****
        try {
            response.getWriter().print("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void registerService(HttpServletRequest request, HttpServletResponse response,Merchant merchant) {

    }

    public void deleteMerchantService(HttpServletRequest request, HttpServletResponse response,Merchant merchant){
    }

    public void updateMerchantService(HttpServletRequest request, HttpServletResponse response,Merchant oldMerchant, Merchant newMerchant) {
    }

    public void selectMerchantAll(HttpServletRequest request, HttpServletResponse response){
    }
    public void selectMerchant(HttpServletRequest request, HttpServletResponse response,Merchant merchant){
    }
    public void insertMerchant(HttpServletRequest request, HttpServletResponse response,Merchant merchant){

    }


    //****************************************与菜品相关操作***********************************

    /*
     * 根据菜品id删除菜品
     * */
    public void deleteDishById(HttpServletRequest request, HttpServletResponse response, Dish dish){
        try {
            if(DBManager.getInst().deleteDishById(dish.getId())){
                response.getWriter().print("true");
            }else{
                response.getWriter().print("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertDishById(HttpServletRequest request, HttpServletResponse response, Dish dish){
        try{
            if(DBManager.getInst().insertDish(dish)){
                response.getWriter().print("true");
            }else{
                response.getWriter().print("false");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void selectAllDish(HttpServletRequest request, HttpServletResponse response, Dish dish){
        ArrayList<Dish> dishList=DBManager.getInst().selectAllDishes();
        try{
            if(dishList==null){
                response.getWriter().print("false");
            }else{
                httpSession=request.getSession();
                httpSession.setAttribute("dishList",dishList);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void selectDishBytype(HttpServletRequest request, HttpServletResponse response, Dish dish){

    }
    public void updataDish(HttpServletRequest request, HttpServletResponse response, Dish dish){

    }


}
