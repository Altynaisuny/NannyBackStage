package main.java.dao;

import java.util.List;
import java.util.Map;

public interface ICommentDao {

    /**
     * 插入保姆评论
     * @param map nannyNo
     * @return
     */
    public int insertNannyComment(Map map);

    /**
     * 插入客户评论
     * @param map customerNo
     * @return
     */
    public int insertCustomerComment(Map map);

    /**
     * 查询评论
     * @param map recordNo
     * @return
     */
    public List<Map> selectComment(Map map);
}
