package main.java.controller;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import main.java.common.CommonMethod;
import main.java.constants.ResultConstant;
import main.java.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {

    private static Log log = LogFactory.getLog(CustomerController.class);

    @Autowired
    ICustomerService customerService;

    /**
     * 客户注册
     * @param jsonParams
     * @param response
     * @throws IOException
     */
    @RequestMapping("/customer/register")
    public void register(@RequestBody String jsonParams, HttpServletResponse response)throws IOException{
        Map returnMap = new HashMap<>();
        HashMap paramMap = CommonMethod.jsonParamToMap(jsonParams);

        String customerNo = CommonMethod.autoProduceCustomerNo();
        paramMap.put("customerNo", customerNo);//自动生成客户唯一标识号
        paramMap.put("createTime", CommonMethod.nowTime());
        paramMap.put("status", "0");

        try{
            customerService.insertCustomer(paramMap);
            returnMap.put("result", ResultConstant.SUCCESS);
            returnMap.put("customerNo", customerNo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("注册新客户失败");
            returnMap.put("result", ResultConstant.FAIL);
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(returnMap));
    }
}
