package com.sc.scbatis.proxy;

import com.sc.scbatis.config.MapperRegister;
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
        MapperRegister.MapperData mapperData = sqlSession.getConfiguration()
                .getMapperRegister()
                .getMapperData(method.getDeclaringClass().getName() + "." + method.getName());
        if (null != mapperData) {
            System.out.println(String.format("SQL:%s，paramter:[%s]", mapperData.getSql(), args[0]));
            return sqlSession.query(mapperData, args[0]);
        } else {
            throw new RuntimeException("未找到path为：" + method.getDeclaringClass().getName() + "." + method.getName() + "的SQL映射对象");
        }
    }
}
