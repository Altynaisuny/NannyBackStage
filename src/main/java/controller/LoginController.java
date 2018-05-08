package main.java.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import main.java.common.CommonMethod;
import main.java.constants.ResultConstant;
import main.java.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import main.java.service.ILoginService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public void login(@RequestBody String jsonParams, HttpServletResponse response) throws IOException {
        HashMap<String, Object> returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        paramMap.put("password", Md5Utils.stringMD5(paramMap.get("password").toString()));
        List selectResultList = loginService.selectLogin(paramMap);

        if (null != selectResultList && !selectResultList.isEmpty()){
            returnMap.put("result", ResultConstant.SUCCESS);
            returnMap.put(ResultConstant.MESSAGE, "登录成功");

            HashMap dataMap = (HashMap)selectResultList.get(0);
            returnMap.put("id", dataMap.get("id"));

            if ("保姆".equals(paramMap.get("sign").toString())){
                returnMap.put("sign", "保姆");
                returnMap.put("name", dataMap.get("nannyName"));
                returnMap.put("nannyNo", dataMap.get("nannyNo"));
            } else {
                returnMap.put("sign", "客户");
                returnMap.put("name", dataMap.get("customerName"));
                returnMap.put("customerNo", dataMap.get("customerNo"));
            }
        }else {
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "登录失败");
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

}
