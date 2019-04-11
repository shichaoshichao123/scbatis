package com.sc.scbatis.executor;

import com.sc.scbatis.config.Configuration;
import com.sc.scbatis.config.MapperRegister;
import com.sc.scbatis.handler.result.ResultHandler;
import com.sc.scbatis.handler.statement.StatementHandler;
import lombok.Data;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: 默认的Excutor
 */
@Data
public class DefaultExecutor implements Executor {

    private final Configuration configuration;
    private final ResultHandler resultHandler;

    public DefaultExecutor(Configuration configuration) {
        this.configuration = configuration;
        this.resultHandler = new ResultHandler(configuration);
    }

    public <T> T query(MapperRegister.MapperData mapperData, Object parameter) throws Exception {
        StatementHandler statementHandler = new StatementHandler(resultHandler, configuration);
        return statementHandler.query(mapperData, parameter);
    }
}
