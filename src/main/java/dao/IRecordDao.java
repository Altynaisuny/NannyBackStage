package main.java.dao;

import java.util.List;
import java.util.Map;

public interface IRecordDao {
    /**
     * 发布新的服务订单
     * @param map
     * @return
     */
    public int insertRecord(Map map);

    /**
     * 查看所有订单
     * @param map customerNo or nannyNo or status
     * @return
     */
    public List<Map> selectPageRecordList(Map map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    public int selectCount(Map map);
    /**
     * 订单详情
     * @param map recordNo
     * @return
     */
    public Map selectRecordDetail(Map map);

    /**
     * 选定保姆，更新recordInfo的nannyNo ,status ,startTime字段
     * @param map recordNo nannyNo
     * @return
     */
    public int choseNannyOfRecord(Map map);


    /**
     * 保姆提出申请
     * @param map
     * @return
     */
    public int insertRecordApply(Map map);

    /**
     * 查询该订单有哪些保姆申请
     * @param map recordNo
     * @return
     */
    public List<Map> selectRecordApply(Map map);

    /**
     * 查询该客户发布的所有订单
     * @param map
     * @return
     */
    public List<Map> selectPagePublishRecord(Map map);

    /**
     * 该客户一共发布了多少订单
     * @param map
     * @return
     */
    public int countPublishRecord(Map map);

    public int cancelNannyOfRecord(Map map);

    /**
     * 关闭订单
     * @return
     */
    public int updateRecordToClose(Map map);
}
