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
     * 选定保姆，更新record的nannyNo 字段
     * @param map
     * @return
     */
    public int updateRecordNannyNo(Map map);

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
}
