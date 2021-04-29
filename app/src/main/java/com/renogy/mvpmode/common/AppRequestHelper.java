package com.renogy.mvpmode.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Create by 17474 on 2021/4/28.
 * Email： lishuwentimor1994@163.com
 * Describe：app内 所有请求接口数据的辅助类
 */
public class AppRequestHelper {

    /**
     * @param searchType               搜索类型
     * @param searchCriteria           搜索条件
     * @param theEndPostUpdateDatetime 最后的更新时间
     * @return 符合条件的map集合
     */
    private static Map<String, String> getLoadPostMap(String searchType, String searchCriteria,
                                                      String theEndPostUpdateDatetime) {
        Map<String, String> map = new HashMap<>();
        map.put("searchType", searchType);
        map.put("searchCriteria", searchCriteria);
        map.put("theEndPostUpdateDatetime", theEndPostUpdateDatetime);
        return map;
    }

    public static Map<String, String> loadRecommendPost(String theEndPostUpdateDatetime) {
        return getLoadPostMap("0", "", theEndPostUpdateDatetime);
    }

    public static Map<String, String> loadFollowPost(String theEndPostUpdateDatetime) {
        return getLoadPostMap("1", "控制器", theEndPostUpdateDatetime);
    }

}
