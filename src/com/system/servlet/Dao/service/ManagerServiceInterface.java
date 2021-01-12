package com.system.servlet.Dao.service;

import com.system.beans.Merchant;

/**
 * @author nanfang
 * @create 2021/01/11/16:33
 */
public interface ManagerServiceInterface {
    public String loginService(Merchant merchant);
    public String registerService(Merchant merchant);
    public String deleteManagerService(Merchant merchant);
    public String updateManagerService(Merchant oldMerchant, Merchant newMerchant);
}
