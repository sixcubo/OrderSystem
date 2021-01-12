package com.system.servlet.Dao.service;

import com.system.beans.Users;

/**
 * @author nanfang
 * @create 2021/01/11/16:28
 */
public interface UserServiceInterface {
    public String loginService(Users users);
    public String registerService(Users users);
    public String deleteUserService(Users users);
    public String updateUserService(Users oldUser,Users newUser);
}
