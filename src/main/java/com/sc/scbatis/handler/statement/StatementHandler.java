package com.sc.scbatis.handler.statement;

import com.mysql.jdbc.StringUtils;
import com.sc.scbatis.config.Configuration;
import com.sc.scbatis.config.MapperRegister;
import com.sc.scbatis.db.DataBaseUtil;
import com.sc.scbatis.handler.result.ResultHandler;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author: shichao
 * @date: 2019/4/11
 * @description: 用于处理数据库相关Statement处理
 */
@Data
public class StatementHandler {


    /**
     * 用于查询结果的处理
     */
    private final ResultHandler resultHandler;
    /**
     * 配置对象
     */
    private final Configuration configuration;


    public StatementHandler(ResultHandler resultHandler, Configuration configuration) {
        this.resultHandler = resultHandler;
        this.configuration = configuration;
    }

    public <T> T query(MapperRegister.MapperData mapperData, Object parameter) throws Exception {
        try {
            Connection conn = DataBaseUtil.getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(mapperData.getSql());
            preparedStatement.setInt(1, Integer.parseInt(String.valueOf(parameter)));
            preparedStatement.execute();
            return resultHandler.handle(preparedStatement, mapperData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 进行查询参数的绑定
     *
     * @param preparedStatement
     * @param parameters
     * @return
     */
    private PreparedStatement doParameterBinding(PreparedStatement preparedStatement, Object... parameters) {
        if (null == preparedStatement || null == parameters) {
            throw new RuntimeException("查询参数绑定异常");
        }
        for (int i = 0; i < parameters.length; i++) {

        }

        return preparedStatement;

    }

    private void binding(PreparedStatement preparedStatement, Object parameter) {

    }
}
