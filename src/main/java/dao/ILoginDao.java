package main.java.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ILoginDao {
    /**
     * 登录
     * @param map
     * @return
     */
    public List<Map> selectLogin(HashMap map);
}
