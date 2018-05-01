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
    INannyDao iNannyDao;
    @Override
    public int insertNanny(Map map) {
        return iNannyDao.insertNanny(map);
    }

    @Override
    public int updateNannyPassword(Map map) {
        return iNannyDao.updateNannyPassword(map);
    }

    @Override
    public Map selectNannyByNo(Map map) {
        List<Map> list = iNannyDao.selectNannyByNo(map);
        if (list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }
    }
}
