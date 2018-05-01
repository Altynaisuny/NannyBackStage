package main.java.service.Impl;

import main.java.dao.ICommentDao;
import main.java.dao.IRecordDao;
import main.java.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    IRecordDao iRecordDao;

    @Autowired
    ICommentDao iCommentDao;

    @Override
    public int insertRecord(Map map) {
        return iRecordDao.insertRecord(map);
    }

    @Override
    public List<Map> selectUnclosedRecordListByCustomerNo(Map map) {
        return null;
    }

    @Override
    public List<Map> selectRecordListUnclosed(Map map) {
        map.put("status", 0);//添加未结束条件
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());

        map.put("offset", (currentPage-1)*pageSize);
        map.put("limit", map.get("pageSize").toString());
        return iRecordDao.selectPageRecordList(map);
    }

    @Override
    public int selectCount(Map map) {
        return iRecordDao.selectCount(map);
    }

    @Override
    public Map selectRecordDetail(Map map) {
        return iRecordDao.selectRecordDetail(map);
    }

    @Override
    public int updateRecordNannyNo(Map map) {
        return 0;
    }

    @Override
    public int insertRecordApply(Map map) {
        return iRecordDao.insertRecordApply(map);
    }

    @Override
    public int updateRecordScore(Map map) {
        return 0;
    }

    @Override
    public int insertRecordComment(Map map) {
        if (map.get("NO").toString().startsWith("NO") ){
            //客户
            map.put("customerNo",map.get("NO"));
            return iCommentDao.insertCustomerComment(map);
        }
        if (map.get("NO").toString().startsWith("NA")){
            //保姆
            map.put("nannyNo", map.get("NO"));
            return iCommentDao.insertNannyComment(map);
        }
        return 0;
    }

    @Override
    public List<Map> selectRecordComment(Map map) {
        return iCommentDao.selectComment(map);
    }

}
