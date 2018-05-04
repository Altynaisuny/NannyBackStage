package main.java.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import main.java.common.CommonMethod;
import main.java.constants.ResultConstant;
import main.java.service.INannyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class NannyController {

    private static Log log = LogFactory.getLog(NannyController.class);

    @Autowired
    INannyService iNannyService;

    /**
     * 保姆申请
     * 获取编号成功并返回
     * @param response
     * @param jsonParams
     */
    @RequestMapping(value = "/apply")
    public void apply(@RequestBody String jsonParams, HttpServletResponse response)throws  IOException{
        HashMap<String, Object> returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
        //todo 此处防止用户重复性注册，进行校验

        String nannyNo = CommonMethod.autoProduceNannyNo();
        paramMap.put("nannyNo", nannyNo);//自动生成保姆编号
        try{
            iNannyService.insertNanny(paramMap);
            returnMap.put("result", ResultConstant.SUCCESS);
            returnMap.put("nannyNo", nannyNo);//返回注册保姆编号
        }catch (Exception e){
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "注册第一步失败！");
            log.error("注册保姆第一步失败" + e);
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    /**
     * 更改密码
     * @param jsonParams  nannyNo password
     * @param response
     */
    @RequestMapping("/firstSetPassword")
    public void updatePassword(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        HashMap<String, Object> returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        //检查该用户是否有密码
        if (whetherHavePassword(paramMap)){
            //有密码
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "该用户已存在密码！");
        } else {
            try {
                iNannyService.updateNannyPassword(paramMap);
                returnMap.put("result", ResultConstant.SUCCESS);
            }catch (Exception e){
                returnMap.put("result", ResultConstant.FAIL);
                returnMap.put(ResultConstant.MESSAGE, "更改密码错误！");
                log.error("更改密码错误" + e);
            }
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    /**
     * 检查是不是存在密码
     * @param paramMap
     * @return
     */
    private boolean whetherHavePassword (Map paramMap){
        Map m = iNannyService.selectNannyByNo(paramMap);
        if (null == m.get("password")){
            return false;
        }
        return true;
    }

    /**
     * 检查是否有申请资格
     * 此处的功能以后再加，关于防止用户重复注册的模块
     */
    public void check(){

    }

    /**
     * 获取nanny中，黑名单 ，改成评分倒叙  查询
     */
    public  void getBlackList(){

    }

}
