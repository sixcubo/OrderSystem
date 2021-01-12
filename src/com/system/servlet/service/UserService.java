package com.system.servlet.service;
import com.system.beans.Users;

/**
 * @author nanfang
 * @create 2021/01/11/14:43
 */

public class UserService  {
    /*
    * 用户登录
    * */
    public String loginService(Users users){
        int id= users.getId();
        return "";
    }
    /*
    * 用户注册
    * */
    public String registerService(Users users){
        return "";
    }
    /*
    * 删除用户
    * */
    public String deleteUserService(Users users) {
        return null;
    }

    /*
    * 用户更新
    * */
    public String updateUserService(Users oldUser, Users newUser) {
        return null;
    }

    /*
    *查找单个用户
    */
    public String selectUserService(Users user) {
        return null;
    }
    /*
    * 查找所有用户
    * */
}
