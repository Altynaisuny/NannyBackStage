package main.java.service.Impl;

import main.java.dao.ILoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.service.ILoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    ILoginDao loginDao;

    @Override
    public List<Map> selectLogin(HashMap map) {
        return loginDao.selectLogin(map);
    }
}
