package com.sc.scbatis.handler.result;

import com.mysql.jdbc.StringUtils;
import com.sc.scbatis.config.Configuration;
import com.sc.scbatis.config.MapperRegister;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: shichao
 * @date: 2019/4/11
 * @description: 用于进行结果的处理
 */
@Data
public class ResultHandler {

    private final Configuration configuration;

    public ResultHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 进行查询结果的处理
     *
     * @param preparedStatement
     * @param mapperData
     * @param <T>
     * @return
     */
    public <T> T handle(PreparedStatement preparedStatement, MapperRegister.MapperData mapperData) throws InstantiationException, IllegalAccessException, SQLException, NoSuchMethodException, InvocationTargetException {
        Object resultObj = new DefaultObjectFactory().createOriginalObject(mapperData.getClazz());
        ResultSet rs = preparedStatement.getResultSet();
        if (rs.next()) {
            //进行对象赋值
            for (Field file : resultObj.getClass().getDeclaredFields()) {
                this.setValue(resultObj, file, rs);
            }

        }
        return (T) resultObj;
    }

    /**
     * 赋值具体操作
     *
     * @param resultObj
     * @param file
     * @param rs
     */
    private void setValue(Object resultObj, Field file, ResultSet rs) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method method = resultObj.getClass().getMethod("set" + upperCapital(file.getName()), file.getType());
        method.invoke(resultObj, getValue(file, rs));
    }

    /**
     * 从rs中获取值
     *
     * @param file
     * @param rs
     * @return
     */
    private Object getValue(Field file, ResultSet rs) throws SQLException {
        //TODO 这里值处理了整型和String类型的之后可以进行类型的拓展
        Class<?> tClass = file.getType();
        if (Integer.class == tClass) {
            return rs.getInt(file.getName());
        } else if (String.class == tClass) {
            return rs.getString(file.getName());
        } else {
            return rs.getString(file.getName());

        }
    }

    /**
     * 首字母转大写
     *
     * @param name
     * @return
     */
    private String upperCapital(String name) {
        if (!StringUtils.isNullOrEmpty(name)) {
            return (new StringBuilder()).append(Character.toUpperCase(name.charAt(0))).append(name.substring(1)).toString();
        }
        return name;
    }
}
