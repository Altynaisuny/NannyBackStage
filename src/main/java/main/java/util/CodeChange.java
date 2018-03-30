package main.java.util;
import java.io.UnsupportedEncodingException;
import java.util.Map;
/**
 * 转换编码
 * @author sunyt
 *
 */
@SuppressWarnings({"rawtypes"})
public class CodeChange {

    /**
     * iso8859-1 to utf-8
     * @param param
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map toUTF8(Map<Object,Object> param) throws UnsupportedEncodingException{
        for ( Map.Entry entry : param.entrySet()) {
            String key = entry.getKey().toString();
            if(entry.getValue() != null){
                param.put(key,new String(entry.getValue().toString().getBytes("iso8859-1"),"utf-8"));
            }
        }
        return param;
    }

    /**
     * 特殊字符转义，如：<、>、"、& 等等
     */
    private static Object specialCharEscape(Object src){
        if (src == null) {
            return "";
        }
        String source = src.toString();
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case '"':
                    buffer.append("&quot;");
                    break;
                case 10:
                case 13:
                    break;
                default:
                    buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }
}
