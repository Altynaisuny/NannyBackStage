package controller;

import constants.ResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ILoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    ILoginService loginService;

    /**
     * 登录
     * @param m
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(@RequestParam Map m){
        HashMap paramMap = new HashMap();
        paramMap.put("userNo", m.get("userNo").toString());
        paramMap.put("password", m.get("password").toString());
        System.out.printf("hahah,correct");
        HashMap returnMap = new HashMap();

        if(!loginValidate(paramMap, returnMap)){
            returnMap.put("result", ResultConstant.ERROR);
            returnMap.put(ResultConstant.MESSAGE, "校验失败");
        }

        List selectResultList = loginService.selectLogin(paramMap);
        if ("1".equals(selectResultList.size())){
            returnMap.put(ResultConstant.MESSAGE ,"登录成功");
        }else {
            returnMap.put(ResultConstant.MESSAGE ,"登录失败");
        }

        return "";
    }

    /**
     * 登录校验
     * @param paramMap
     * @param returnMap
     * @return
     */
    public boolean loginValidate(HashMap paramMap, HashMap returnMap){
        return true;
    }


}
