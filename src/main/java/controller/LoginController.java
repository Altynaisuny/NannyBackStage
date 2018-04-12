package main.java.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import main.java.common.CommonMethod;
import main.java.constants.ResultConstant;
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
    public void login(@RequestBody String jsonParams, HttpServletResponse response) throws IOException {
        HashMap returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        List selectResultList = loginService.selectLogin(paramMap);
        if (selectResultList.size() == 1){
            returnMap.put("result", ResultConstant.SUCCESS);
            returnMap.put(ResultConstant.MESSAGE, "登录成功");
        }else {
            returnMap.put("result", ResultConstant.ERROR);
            returnMap.put(ResultConstant.MESSAGE, "登录失败");
        }
        response.getWriter().print(JSON.toJSONString(returnMap));
        System.out.println(JSON.toJSONString(returnMap));
        response.setContentType("text/plain;charset=UTF-8");
    }
    @RequestMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("main");
    }


}
