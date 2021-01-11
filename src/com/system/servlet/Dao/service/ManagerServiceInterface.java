package com.system.servlet.Dao.service;

import com.system.beans.Manager;

/**
 * @author nanfang
 * @create 2021/01/11/16:33
 */
public interface ManagerServiceInterface {
    public String loginService(Manager manager);
    public String registerService(Manager manager);
    public String deleteManagerService(Manager manager);
    public String updateManagerService(Manager oldManager,Manager newManager);
}
