package com.sc.scbatis.config;

import com.mysql.jdbc.StringUtils;
import lombok.Data;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: Configuration用于scbatis存放配置信息的对象
 */
@Data
public class Configuration {

    /**
     * 配置扫描路径
     */
    private String scanPath;

    /**
     * mapper注册类
     */
    private MapperRegister mapperRegister;

    public Configuration doScan(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public void build() {
        if (StringUtils.isNullOrEmpty(scanPath)) {
            throw new RuntimeException("扫描路径不存在。。。");
        }
    }

}
