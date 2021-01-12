package com.system.servlet.service;



import com.system.beans.Merchant;
import com.system.servlet.database.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author nanfang
 * @create 2021/01/11/16:38
 */

public class MerchantService   {
    public void loginService(HttpServletRequest request, HttpServletResponse response,Merchant merchant) {
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
}
