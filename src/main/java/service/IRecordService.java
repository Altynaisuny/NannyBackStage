package main.java.service;

import java.util.List;
import java.util.Map;

public interface IRecordService {

    /**
     * 发布新的服务订单
     * @param map
     * @return
     */
    public int insertRecord(Map map);

    /**
     * 客户查看自己的订单（已结束）
     * @param map
     * @return
     */
    public List<Map> selectUnclosedRecordListByCustomerNo(Map map);

    /**
     * 保姆,游客
     * 查看所有订单（未结束）
     * @param map
     * @return
     */
    public List<Map> selectRecordListUnclosed(Map map);

    public int selectCount(Map map);
    /**
     * 订单详情
     * @param map recordNo
     * @return
     */
    public Map selectRecordDetail(Map map);

    /**
     * 选定保姆
     * 更新 record_info 中的 status  startTime nannyNo
     * @param map
     * @return
     */
    public int choseNannyOfRecord(Map map);

    /**
     * 保姆申请订单
     * @param map
     * @return
     */
    public int insertRecordApply(Map map);

    /**
     * 更新本次记录的评分
     * @param map
     * @return
     */
    public int updateRecordScore(Map map);

    /**
     * 插入评论
     * @param map
     * @return
     */
    public int insertRecordComment(Map map);

    /**
     * 查询评论
     * @param map
     * @return
     */
    public List<Map> selectRecordComment(Map map);

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

    /**
     * 该订单有哪些保姆申请了
     * @param map
     * @return
     */
    public List<Map> selectRecordApply(Map map);

    /**
     * 关闭订单
     * @param map status  evaluate  endTime score
     * @return
     */
    public int updateRecordToClose(Map map);
}
