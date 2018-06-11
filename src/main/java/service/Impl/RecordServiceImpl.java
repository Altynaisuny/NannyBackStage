package main.java.service.Impl;

import main.java.constants.RecordConstant;
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
        //todo 这里有点问题，向外界展示的订单，状态
        int currentPage = 1;
        int pageSize = 10;
        if (map.containsKey("currentPage") && map.containsKey("pageSize")){
            try{
                currentPage = Integer.parseInt(map.get("currentPage").toString());
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            } catch (Exception e){
                e.printStackTrace();
            }
            map.put("offset", (currentPage-1)*pageSize);
            map.put("limit", map.get("pageSize").toString());

            return iRecordDao.selectPageRecordList(map);
        } else {
            return null;
        }

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
    public int choseNannyOfRecord(Map map) {
        return iRecordDao.choseNannyOfRecord(map);
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

    @Override
    public List<Map> selectPagePublishRecord(Map map) {
        return iRecordDao.selectPagePublishRecord(map);
    }

    @Override
    public int countPublishRecord(Map map) {
        return iRecordDao.countPublishRecord(map);
    }

    @Override
    public List<Map> selectRecordApply(Map map) {
        return iRecordDao.selectRecordApply(map);
    }

    @Override
    public int updateRecordToClose(Map map) {
        return iRecordDao.updateRecordToClose(map);
    }

}
