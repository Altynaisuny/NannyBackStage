package main.java.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.service.ILoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    ILoginService loginService;
    public List<Map> selectLogin(HashMap map) {
        return loginService.selectLogin(map);
    }
}
