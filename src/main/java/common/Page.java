package main.java.common;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import java.util.Map;

/**
 * @author sunyt
 * 页码
 */
public class Page {

    private static Log log = LogFactory.getLog(Page.class);


    /**
     *
     * @param paramMap currentPage当前页码  pageSize每页行数
     * @return
     */
    public static Map<String, Object> newPage(Map<String, Object> paramMap) {
        int page = 0;
        int rows = 0;
        //参数校验
        if (null == paramMap.get("currentPage")){
            page = 1;
        }
        if (null == paramMap.get("pageSize")){
            rows = 10;
        }

        try {
            page= Integer.parseInt(paramMap.get("currentPage").toString());
            rows = Integer.parseInt(paramMap.get("pageSize").toString());
        }catch (Exception e){
            e.printStackTrace();
            log.error("分页参数有问题");
        }
        paramMap.put("offset", rows * (page - 1));
        paramMap.put("limit", rows);

        return paramMap;
    }
}
