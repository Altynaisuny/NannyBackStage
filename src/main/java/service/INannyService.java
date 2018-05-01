package main.java.service;

import java.util.Map;

public interface INannyService {
    /**
     * 注册保姆
     * @param map
     * @return
     */
    public int insertNanny(Map map);

    /**
     * 更改密码
     * @param map
     * @return
     */
    public int updateNannyPassword(Map map);

    /**
     * 根据编号查询保姆信息
     * @param map
     * @return
     */
    public Map selectNannyByNo(Map map);
}
