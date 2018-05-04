package main.java.service.Impl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import main.java.dao.ILoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.service.ILoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {

    private static Log log = LogFactory.getLog(LoginServiceImpl.class);

    @Autowired
    ILoginDao loginDao;

    @Override
    public List<Map> selectLogin(Map map){

        if (null == map.get("user") ){
            return null;
        }
        try {
            if (map.get("sign").toString().equals("保姆")){
                //保姆
                if (map.get("user").toString().startsWith("NA")){
                    map.put("nannyNo", map.get("user"));//使用编号登录
                } else {
                    map.put("mobile", map.get("user"));//使用手机登录
                }
                return loginDao.selectLoginNanny(map);
            } else {
                //客户
                if (map.get("user").toString().startsWith("NO")){
                    map.put("customerNo", map.get("user"));
                } else {
                    map.put("mobile", map.get("user"));
                }
                return loginDao.selectLoginCustomer(map);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("登录sign出错" + e);
        }
        return null;
    }
}
