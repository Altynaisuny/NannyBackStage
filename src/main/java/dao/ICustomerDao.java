package main.java.dao;

import java.util.Map;

public interface ICustomerDao {

    /**
     * 以保姆角色登录
     * @param map
     * @return
     */
    public Map selectNannyLogin(Map map);

    /**
     * 以客户角色登录
     * @param map
     * @return
     */
    public Map selectCustomerLogin(Map map);

}
