package main.java.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import main.java.common.CommonMethod;
import main.java.common.Page;
import main.java.constants.RecordConstant;
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

        String jsonString = JSONArray.toJSONString(resultList);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", jsonArray);
        jsonObject.put("rows", rows);

        response.setContentType("text/plain;charset=UTF-8");
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
        paramMap.put("occurTime", CommonMethod.nowTime());
        if(checkInsertRecordApply(paramMap, returnMap)){
            try {
                recordService.insertRecordApply(paramMap);
                returnMap.put("result", ResultConstant.SUCCESS);
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

        if (null == paramMap.get("status") || "1".equals(paramMap.get("status"))){
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "该订单已经关闭");
            return false;
        }

        //todo 这里还要加一层，查询该保姆有没有还未结束的订单
        //todo 还有一层，防止重复提交。已经存在就不允许重复提交。。

        return true;
    }

    /**
     * 查询订单申请列表
     * @param jsonParams recordNo
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/selectNannyApplyList")
    public void selectRecordApply(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        List resultList = null;
        Map recordMap = null;
        if (validateSelectRecordApply(paramMap, returnMap)){
            try {
                resultList = recordService.selectRecordApply(paramMap);
                recordMap = recordService.selectRecordDetail(paramMap);
            } catch (Exception e){
                e.printStackTrace();
                returnMap.put("result", ResultConstant.FAIL);
            }
        }
        //list 转化为 jsonArray
        String jsonString = JSONArray.toJSONString(resultList);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("list", jsonArray);
        jsonObject.put("recordInfo", JSONObject.parse(JSON.toJSONString(recordMap)));
        jsonObject.put("result", JSONObject.parse(JSON.toJSONString(returnMap)));
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    private boolean validateSelectRecordApply(Map paramMap, Map returnMap){
        if (null == paramMap.get("recordNo") || "".equals(paramMap.get("recordNo"))) {
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "订单号为空");
            return false;
        }

        if(null == paramMap.get("customerNo") || "".equals(paramMap.get("customerNo"))){
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "客户编号为空");
            return false;
        }
        return true;
    }
    /**
     * 查询客户发布的所有订单
     * @param jsonParams customerNo status currentPage pageSize
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/selectCustomerRecordPublish")
    public void selectRecordPublishByCustomer(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
        List resultList = null;

        int rows = recordService.countPublishRecord(paramMap);
        try {
            resultList =  recordService.selectPagePublishRecord(Page.newPage(paramMap));
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询客户发布订单失败！");
        }

        String jsonString = JSONArray.toJSONString(resultList);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", jsonArray);
        jsonObject.put("rows", rows);

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    /**
     * 选定保姆
     * @param jsonParams recordNo nannyNo
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/choseNanny")
    public void choseRecordNanny(@RequestBody String jsonParams, HttpServletResponse response) throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        paramMap.put("status", "1");//订单状态置为1(已选定)
        paramMap.put("startTime", CommonMethod.nowTime());
        //校验能否选定
        if (validateChoseRecordNanny(paramMap, returnMap)){
            try {
                recordService.choseNannyOfRecord(paramMap);
                returnMap.put("result", ResultConstant.SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
                returnMap.put("result", ResultConstant.FAIL);
                returnMap.put(ResultConstant.MESSAGE, "选定保姆失败");
            }
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    /**
     * 校验是否可以选定该保姆
     */
    public boolean validateChoseRecordNanny(Map paramMap, Map returnMap){
        Map recordMap = null ;
        try {
            recordMap = recordService.selectRecordDetail(paramMap);
        } catch (Exception e){
            e.printStackTrace();
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "记录不存在！");
        }
        if (null ==recordMap.get("nannyNo") || "".equals(recordMap.get("nannyNo").toString())){
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "保姆已锁定，请先取消");
            return true;
        }
        return false;
    }

    /**
     * 取消保姆选定
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/cancelChose")
    public void cancelNannyChose(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
        paramMap.put("startTime", null);
        paramMap.put("status", RecordConstant.CREATE);
        paramMap.put("nannyNo", null);
        try {
            recordService.choseNannyOfRecord(paramMap);
            returnMap.put("result", ResultConstant.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("result", ResultConstant.FAIL);
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    /**
     * 关闭（结束）订单
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/record/close")
    public void closeRecord (@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);
        paramMap.put("endTime", CommonMethod.nowTime());

        //校验是否可以关闭
        if (checkCloseRecord(paramMap, returnMap)){
            try {
                recordService.updateRecordToClose(paramMap);
                returnMap.put("result", ResultConstant.SUCCESS);
            } catch (Exception e){
                e.printStackTrace();
                returnMap.put("result", ResultConstant.FAIL);
                returnMap.put(ResultConstant.MESSAGE, "失败！");
            }
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }

    public boolean checkCloseRecord (Map paramMap, Map returnMap){
        //订单不存在，返回错误
        if (null == paramMap.get("recordNo")){
            returnMap.put("result", ResultConstant.FAIL);
            returnMap.put(ResultConstant.MESSAGE, "订单不存在！");
            return false;
        }
        try {
            Map resultMap = recordService.selectRecordDetail(paramMap);
            //只有状态为被预约状态，才能关闭。
            if (RecordConstant.RESERVE.equals(resultMap.get("status").toString()) || RecordConstant.END.equals(resultMap.get("status").toString())){
                paramMap.put("status", RecordConstant.END);//状态置为关闭
                return  true;
            } else {
                returnMap.put("result", ResultConstant.FAIL);
                returnMap.put(ResultConstant.MESSAGE, "订单状态错误！");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}