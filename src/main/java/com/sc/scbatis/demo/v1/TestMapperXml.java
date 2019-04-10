package com.sc.scbatis.demo.v1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: 模拟Map的Xml配置
 */
public class TestMapperXml {

    public static final String nameSpace = "com.sc.scbatis.demo.v1.TestMapper";

    public static final Map<String, String> methodMap = new ConcurrentHashMap<String, String>(64);

    static {
        methodMap.put("getById", "select * from user where id = ?");
    }
}
