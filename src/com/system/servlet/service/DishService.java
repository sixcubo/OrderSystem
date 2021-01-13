package com.system.servlet.service;

import com.system.beans.Dish;
import com.system.servlet.database.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author nanfang
 * @create 2021/01/12/13:21
 */
public class DishService {
    HttpSession httpSession;
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
