package com.sc.scbatis.session;

import com.sc.scbatis.config.Configuration;
import com.sc.scbatis.config.MapperRegister;
import com.sc.scbatis.executor.DefaultExecutor;
import com.sc.scbatis.executor.Executor;
import com.sc.scbatis.proxy.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: SqlSession 是一个框架的关键对象，用于进行相关调用，通过委托的方式 将具体操作委托给Executer和SqlSession
 */
public class SqlSession {

    private Executor executor;
    private Configuration configuration;


    public SqlSession(Configuration configuration) {
        this.executor = new DefaultExecutor(configuration);
        this.configuration = configuration;
    }

    public SqlSession(Executor executor, Configuration configuration) {
        this.executor = executor;
        this.configuration = configuration;
    }

    /**
     * 通过传入指定的Mapper或得对应的Mapper代理对象
     * 这里使用的是Java提供的动态代理
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MapperProxy(this, clazz));
    }

    /**
     * 简单的查询方法
     * V1版本
     *
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement, Object parameter) throws Exception {
        return executor.queryV1(statement, parameter);
    }

    /**
     * 简单的查询方法
     * V2版本
     *
     * @param mapperData
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T query(MapperRegister.MapperData mapperData, Object parameter) throws Exception {
        return executor.query(mapperData, parameter);
    }

    public Executor getExecutor() {
        return executor;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
