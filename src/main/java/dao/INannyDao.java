package main.java.dao;

import java.util.List;
import java.util.Map;

public interface INannyDao {
    /**
     * 保姆从业申请
     *
     * @param map
     * @return
     */
    public int insertNanny(Map map);

    /**
     * 修改密码
     * @param map
     * @return
     */
    public int updateNannyPassword(Map map);

    /**
     * 查询保姆信息通过nannyNo查询
     * @param map  id
     * @return
     */
    public List selectNannyByNo(Map map);

    /**
     * 查询保姆信息，分页
     * @param map
     * @return
     */
    public List selectNannyPage(Map map);

    /**
     * 更新保姆编号
     * @param map
     * @return
     */
    public int updateNannyNo(Map map);


}
