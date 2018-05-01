package main.java.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ILoginDao {
    /**
     * 登录(客户)
     * @param map
     * @return
     */
    public List<Map> selectLoginCustomer(Map map);

    /**
     * 登录(保姆)
     * @param map
     * @return
     */
    public List<Map> selectLoginNanny(Map map);
}
