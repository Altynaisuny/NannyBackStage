package main.java.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 一些简单的通用方法
 */
public class CommonMethod {
    /**
     * 自动生成密码
     * @return
     */
    public static String autoProducePassword(){
        char[] charAndnum = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        Random random = new Random(); //用于产生随机数
        String str = "";

        str = "";
        for (int j = 0; j < 8; j++) {
            str += charAndnum[random.nextInt(51)];
        }
        return str;
    }

    /**
     * 自动生成记录号
     * @return
     */
    public static String autoProduceRecordNumber(){
        //使用当前系统时间作为记录ID，保证唯一性
        long recordTime = System.currentTimeMillis();
        return Long.toString(recordTime);
    }

    /**
     *
     * @param jsonStr json字符串
     * @return
     */
    public static HashMap jsonParamToMap (String jsonStr){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        HashMap returnMap = new HashMap();
        for (Object map : jsonObject.entrySet()){
            returnMap.put(((Map.Entry)map).getKey(), ((Map.Entry)map).getValue());
        }
        return returnMap;
    }

    /**
     * 当前时间
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    public  static  String nowTime (){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) {
        //1523256878826
        //这种格式的时间，作为生成记录
        System.out.println(autoProduceRecordNumber());
    }


}
