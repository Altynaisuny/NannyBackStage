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
     * 生成n位随机字符串
     *
     * @param n 位数
     * @return 随机字符串
     */
    public static String autoProduceRandomStr(int n) {
        int length = 0;
        try {
            length = n;
        } catch (Exception e) {
            e.printStackTrace();
        }
        char[] charAndnum = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < length; j++) {
            str.append(charAndnum[random.nextInt(51)]);
        }
        return str.toString();
    }

    /**
     * 自动生成记录号
     * 例如：20180410113644UIAaNPKW
     *
     * @return String 记录号
     */
    public static String autoProduceRecordNumber() {
        //使用当前系统时间作为记录ID，保证唯一性
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date) + autoProduceRandomStr(8);
    }

    /**
     * 生成客户唯一性标识
     *
     * @return
     */
    public static String autoProduceUniqueNo() {
        long recordTime = System.currentTimeMillis();
        return "NO" + recordTime;
    }

    /**
     * 生成保姆账号
     *
     * @return
     */
    public static String autoProduceNannyNo() {
        //使用当前系统时间作为记录ID，保证唯一性
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        return "NA" + sdf.format(date) + autoProduceRandomStr(3);
    }

    /**
     * @param jsonStr json字符串
     * @return map
     */
    public static HashMap jsonParamToMap(String jsonStr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        HashMap<String, Object> returnMap = new HashMap<>();
        for (Object map : jsonObject.entrySet()) {
            returnMap.put(((Map.Entry) map).getKey().toString(), ((Map.Entry) map).getValue());
        }
        return returnMap;
    }


    /**
     * 当前时间
     * yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String nowTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) {
        //1523256878826
        //bEJWmHXJ
        //这种格式的时间，作为生成记录
        System.out.println(autoProduceNannyNo());
    }


}
