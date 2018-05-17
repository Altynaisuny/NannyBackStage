package main.java.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import main.java.common.CommonMethod;
import main.java.common.Page;
import main.java.constants.ResultConstant;
import main.java.service.INannyService;
import main.java.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
        paramMap.put("status", '0');
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
            paramMap.put("password", Md5Utils.stringMD5(paramMap.get("password").toString()));
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
     * 查看该保姆的 从业记录 和个人信息
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/nanny/info")
    public void selectNannyInfo(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        Map paramMap = CommonMethod.jsonParamToMap(jsonParams);
        //客户信息map
        Map nannyInfoMap = null;
        //从业资格list
        List resultList = null;
        //总数
        int rows = 0;
        if (null != paramMap.get("nannyNo") && !"".equals(paramMap.get("nannyNo").toString())){
            try {
                nannyInfoMap = iNannyService.selectDetail(paramMap);
                rows = iNannyService.selectCountRecordHistory(paramMap);
                resultList = iNannyService.selectRecordHistory(Page.newPage(paramMap));
            } catch (Exception e){
                returnMap.put("result", ResultConstant.FAIL);
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("info", JSONObject.parse(JSON.toJSONString(nannyInfoMap)));
        jsonObject.put("result", JSONObject.parse(JSON.toJSONString(returnMap)));
        jsonObject.put("list", JSONArray.parseArray(JSONArray.toJSONString(resultList)));
        jsonObject.put("rows", rows);

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(jsonObject);
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
