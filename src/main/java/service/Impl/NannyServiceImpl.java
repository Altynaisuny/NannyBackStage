package main.java.service.Impl;

import main.java.dao.INannyDao;
import main.java.service.INannyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class NannyServiceImpl implements INannyService {

    @Autowired
    INannyDao nannyDao;
    @Override
    public int insertNanny(Map map) {
        return nannyDao.insertNanny(map);
    }

    @Override
    public int updateNannyPassword(Map map) {
        return nannyDao.updateNannyPassword(map);
    }

    @Override
    public Map selectNannyByNo(Map map) {
        List<Map> list = nannyDao.selectNannyByNo(map);
        if (list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    public Map selectDetail(Map map) {
        return nannyDao.selectDetail(map);
    }

    @Override
    public List<Map> selectRecordHistory(Map map) {
        return nannyDao.selectRecordHistory(map);
    }

    @Override
    public int selectCountRecordHistory(Map map) {
        return nannyDao.selectCountRecordHistory(map);
    }
}
