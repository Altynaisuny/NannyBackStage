package main.java.service;

import java.util.List;
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

    /**
     * 保姆信息详情
     * @param map nannyNo
     * @return
     */
    public Map selectDetail(Map map);

    /**
     * 保姆从业历史
     * @param map nannyNo offset limit
     * @return
     */
    public List<Map> selectRecordHistory(Map map);

    /**
     * 总数
     * @param map
     * @return
     */
    public int selectCountRecordHistory(Map map);
}
