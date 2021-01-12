package com.system.servlet.controller;


import com.system.beans.User;
import com.system.servlet.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author cxh
 * @create 2021/01/11/15:25
 */
public class UserServlet extends HttpServlet {
    private static User user=new User();
    private static UserService userService=new UserService();
    /**
     * 中国电信号码格式验证 **/
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|53|7[37]|8[019]|9[19])\\d{8}$)|(?:^(?:\\+86)?1700\\d{7}$)";

    /**
     * 中国联通号码格式验证**/
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[5]|5[56]|66|7[56]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[7-9]\\d{7}$)";
    /**
     * 简单手机号码校验，校验手机号码的长度和1开头
     */
    private static final String SIMPLE_PHONE_CHECK = "^(?:\\+86)?1\\d{10}$";
    /**
     * 中国移动号码格式验证
     **/
    private static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-4789]|98)\\d{8}$)|(?:^(?:\\+86)?1705\\d{7}$)";

    /**
     * 仅手机号格式校验
     */
    private static final String PHONE_PATTERN = new StringBuilder(300)
            .append(CHINA_MOBILE_PATTERN).append("|")
            .append(CHINA_TELECOM_PATTERN).append("|")
            .append(CHINA_UNICOM_PATTERN).toString();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
//            req.setCharacterEncoding("utf-8");
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

    /**
     * 转义字符串中的[]-^\+*${元字符
     *
     */
    private static String escapeMetacharacterOfStr(String input) {
        String regex = "[-{+*$^\\[\\]\\\\]";
        return input.replaceAll(regex, "\\\\$0");
    }

    /**
     * 仅手机号码校验
     *
     * @param input
     * @return
     */
    public static boolean isPhone(String input) {
        return match(PHONE_PATTERN, input);
    }
    /**
     * 匹配函数
     * @param regex
     * @param input
     * @return
     */
    private static boolean match(String regex, String input) {
        return Pattern.matches(regex, input);
    }

    /*
    *
    * */
    private void toReg(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("toReg");
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setTel(req.getParameter("tel"));
        try {
            resp.getWriter().print(user);
            resp.getWriter().print(userService.registerService(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * 更新账户余额
    * */
    private void toPay(HttpServletRequest req, HttpServletResponse resp) {
        user.setUsername(req.getParameter("username"));
        req.getParameter("consume");//得到今日消费总钱数

        //userService.updateUserService(user,);
    }

    /*
    * 根据用户名查找单个用户数据
    * */
    private void toSearch(HttpServletRequest req, HttpServletResponse resp) {
        user.setUsername(req.getParameter("username"));
        try {
            resp.getWriter().print(userService.selectUserService(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 查找所有用户数据
    * */
    private void toShow(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getWriter().print(userService.selectUserAllService(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void toLogin(HttpServletRequest req, HttpServletResponse resp) {
        //username 为前端传送过来的用户名数据 password 为密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        try {
            resp.getWriter().print(userService.loginService(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: 连接数据库
    }
}
