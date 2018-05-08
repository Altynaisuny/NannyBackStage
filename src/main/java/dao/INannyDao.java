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

    /**
     * 查询该保姆所有订单
     * @param map
     * @return
     */
    public List<Map> selectRecordHistory(Map map);

    /**
     * 总数
     * @param map
     * @return
     */
    public int selectCountRecordHistory(Map map);

    /**
     * 查询保姆信息详情
     * @param map
     * @return
     */
    public Map selectDetail(Map map);

    /**
     * 查询保姆有没有未结束的订单
     * @return
     */
    public List<Map> isNannyHasUnclosedRecord();

}
