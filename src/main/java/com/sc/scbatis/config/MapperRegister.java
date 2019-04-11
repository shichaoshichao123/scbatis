package com.sc.scbatis.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: shichao
 * @date: 2019/4/11
 * @description: 用于存放框架的Mapper配置信息
 */
public class MapperRegister {

    /**
     * 用于存放Mapper对应的mapperMethod的map集合
     */
    private Map<String, MapperData> mapperMap = new ConcurrentHashMap<String, MapperData>(64);

    public class MapperData<T> {
        private String sql;
        private Class<T> clazz;

        public MapperData(String sql, Class<T> clazz) {
            this.sql = sql;
            this.clazz = clazz;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getClazz() {
            return clazz;
        }

        public void setClazz(Class<T> clazz) {
            this.clazz = clazz;
        }
    }

    /**
     * 用于注册mapper
     *
     * @param path
     * @param sql
     * @param clazz
     */
    public void register(String path, String sql, Class clazz) {
        mapperMap.put(path, new MapperData(sql, clazz));
    }


    /**
     * 用于返回指定的MapperData
     * @param path
     * @return
     */
    public MapperData getMapperData(String path) {
        return mapperMap.get(path);

    }
}
