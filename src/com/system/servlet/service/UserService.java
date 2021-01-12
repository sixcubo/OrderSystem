package com.system.servlet.service;
import com.system.beans.Users;
import com.system.servlet.Dao.service.UserServiceInterface;

/**
 * @author nanfang
 * @create 2021/01/11/14:43
 */

public class UserService implements UserServiceInterface {
    public String loginService(Users users){
        int id= users.getId();
        return "";
    }
    public String registerService(Users users){
        return "";
    }

    @Override
    public String deleteUserService(Users users) {
        return null;
    }

    @Override
    public String updateUserService(Users oldUser, Users newUser) {
        return null;
    }
}
