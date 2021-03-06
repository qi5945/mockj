package com.kvn.mockj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by wangzhiyuan on 2018/9/12
 */
public class Mock {
    /**
     *
     * @param template mock模板
     * @return 返回 mock 的 json 字符串
     */
    public static String mock(String template) {
        JSONObject result = new JSONObject();
        JSONObject json = JSON.parseObject(template);
        json.forEach((k, v) -> {
            MockField mockField = MockField.parse(template, k, v.toString());
            result.put(mockField.getName(), mockField.generateMockData());
        });
        return result.toJSONString();
    }

    /**
     *
     * @param template mock模板
     * @return 返回 mock 对象
     */
    public static <T> T mock(String template, Class<T> rtnClass) {
        return JSON.parseObject(mock(template), rtnClass);
    }

}
