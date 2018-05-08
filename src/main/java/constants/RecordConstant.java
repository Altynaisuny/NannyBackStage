package main.java.constants;

/**
 * 订单状态常量
 */
public class RecordConstant {


    /**
     * 无人预定，初始状态
     */
    public static final String CREATE = "0";

    /**
     * 已经有人预定
     */
    public static final String RESERVE = "1";

    /**
     * 已经锁定，不能变成结束
     */
    public static final String LOCK = "3";

    /**
     * 已经关闭
     */
    public static final String END = "2";

}
