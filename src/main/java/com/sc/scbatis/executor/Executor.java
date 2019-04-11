package com.sc.scbatis.executor;

import com.sc.scbatis.config.MapperRegister;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: excuter对象，用于操作数据库
 */
public interface Executor {

    /**
     * 查询单个结果
     * V2版本
     *
     * @param mapperData
     * @param parameter
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T query(MapperRegister.MapperData mapperData, Object parameter) throws Exception;

}
