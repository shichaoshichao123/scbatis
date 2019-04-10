package com.sc.scbatis.executor;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: excuter对象，用于操作数据库
 */
public interface Executor {
    /**
     * 查询单个结果
     *
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement, Object parameter) throws Exception;
}
