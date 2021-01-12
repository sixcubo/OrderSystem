package com.system.servlet.service;
import com.system.beans.User;

import java.util.ArrayList;

/**
 * @author nanfang
 * @create 2021/01/11/14:43
 */

public class UserService  {
    /*
    * 用户登录
    * */
    public String loginService(User user){
        int id= user.getId();
        return "";
    }
    /*
    * 用户注册
    * */
    public String registerService(User user){
        return "";
    }
    /*
    * 删除用户
    * */
    public String deleteUserService(User user) {
        return null;
    }
    /*
    *查找单个用户
    */
    public User selectUserService(User user) {
        return null;
    }
    /*
    * 查找所有用户
    * */
    public ArrayList<User> selectUserAllService(User user){
        return null;
    }
    /*
    * 更新用户信息，账户余额
    * */
    public String updateUserService(User user,int consume){
        return null;
    }
}
