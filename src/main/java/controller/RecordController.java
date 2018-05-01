package main.java.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import main.java.common.CommonMethod;
import main.java.common.Page;
import main.java.constants.ResultConstant;
import main.java.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecordController {

    private static Log log = LogFactory.getLog(RecordController.class);

    @Autowired
    IRecordService recordService;

    /**
     * 客户发布订单
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/release")
    public void insertRecord(@RequestBody String jsonParams, HttpServletResponse response)throws IOException {
        HashMap<String, Object> returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
        //生成订单号
        String recordNo = CommonMethod.autoProduceRecordNumber();
        paramMap.put("recordNo", recordNo);
        //记录订单生成的时间
        paramMap.put("createTime", CommonMethod.nowTime());
        if (insertRecordValidate(paramMap)){
            try{
                recordService.insertRecord(paramMap);
                returnMap.put("result", ResultConstant.SUCCESS);
                //返回订单号
                returnMap.put("recordNo", recordNo);
            }catch (Exception e){
                returnMap.put("result", ResultConstant.FAIL);
                e.printStackTrace();
                log.error("客户发布订单失败" + e);
            }
        } else {
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "未登陆，或者未通过校验！");
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));

    }

    /**
     * 订单发布校验
     * @param map
     * @return
     */
    private boolean insertRecordValidate(Map map){

        if(null == map.get("customerNo") || "".equals(map.get("customerNo").toString())){
            return false;
        }
        return true;
    }

    /**
     * 查询订单
     * 只展示没有结束的订单
     * @param jsonParams
     * @param response
     */
    @RequestMapping("/record/unclosed")
    public void selectRecordList(@RequestBody String jsonParams,HttpServletResponse response)throws IOException{
        HashMap<String, Object> returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
        //数据总数
        int rows = recordService.selectCount(paramMap);
        List resultList = null;
        try {
            resultList =  recordService.selectRecordListUnclosed(Page.newPage(paramMap));

        }catch (Exception e){
            e.printStackTrace();
            log.error("查询未结束订单失败");
        }
        response.setContentType("text/plain;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(resultList);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", jsonArray);
        jsonObject.put("rows", rows);

        response.getWriter().print(jsonObject);
    }

    /**
     * 查询订单详情
     * @param jsonParams
     * @param response
     */
    @RequestMapping("/record/detail")
    public void selectRecordDetail(@RequestBody String jsonParams,HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        try{
            returnMap = recordService.selectRecordDetail(paramMap);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询订单详情失败");
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    /**
     * 插入评论
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/insertComment")
    public void insertComment(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        Map paramMap = CommonMethod.jsonParamToMap(jsonParams);

        paramMap.put("createTime", CommonMethod.nowTime());

        if (null !=paramMap.get("NO") &&  !"".equals(paramMap.get("NO").toString())){
            try {
                recordService.insertRecordComment(paramMap);
                returnMap.put("result", ResultConstant.SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
                returnMap.put("result", ResultConstant.FAIL);
                returnMap.put(ResultConstant.MESSAGE, "插入评论失败");
            }
        } else {
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "未登录！");
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    /**
     * 查询评论
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/selectComment")
    public void selectComment(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
        List resultList = null;
        try{
            resultList = recordService.selectRecordComment(paramMap);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setContentType("text/plain;charset=UTF-8");
        String jsonString = JSONArray.toJSONString(resultList);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        response.getWriter().print(jsonArray);
    }

    /**
     * 保姆申请订单
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/apply")
    public void insertRecordApply(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        if(checkInsertRecordApply(paramMap, returnMap)){
            try {
                recordService.insertRecordApply(paramMap);
            }catch (Exception e){
                e.printStackTrace();
                log.error("申请订单失败");
            }
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    /**
     * 保姆申请订单校验
     * @param paramMap
     * @param returnMap
     * @return
     */
    public boolean  checkInsertRecordApply(Map paramMap,Map returnMap){
        if(null == paramMap.get("recordNo") || "".equals(paramMap.get("recordNo").toString())){
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "订单号为空");
            return false;
        }

        if(null == paramMap.get("nannyNo") || "".equals(paramMap.get("nannyNo").toString())){
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "保姆编号为空");
            return false;
        }

        return true;
    }
}
