package com.sc.scbatis.proxy;

import com.sc.scbatis.demo.TestMapperXml;
import com.sc.scbatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: MapperProxy mapper的动态代理类
 */
public class MapperProxy<T> implements InvocationHandler {

    /**
     * 传入sqlSession对象
     */
    private final SqlSession sqlSession;
    /**
     * 被代理的目标类型
     */
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> clazz) {
        this.sqlSession = sqlSession;
        this.mapperInterface = clazz;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String s = method.getDeclaringClass().getName();
        String s1 = TestMapperXml.nameSpace;
        if (method.getDeclaringClass().getName().equals(TestMapperXml.nameSpace)) {
            String sql = TestMapperXml.methodMap.get(method.getName());
            System.out.println(String.format("sql:[%s],parameter:[%s]", sql, args[0]));
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return null;
    }
}
