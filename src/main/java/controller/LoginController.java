package main.java.controller;

import com.alibaba.fastjson.JSONObject;
import main.java.common.CommonMethod;
import main.java.constants.ResultConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import main.java.service.ILoginService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    ILoginService loginService;

    /**
     * 登录
     * @param jsonParams
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public HashMap login(@RequestBody String jsonParams){
        HashMap returnMap = new HashMap();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
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
        return returnMap;
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
