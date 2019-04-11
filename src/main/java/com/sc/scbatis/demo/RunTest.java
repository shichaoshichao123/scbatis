package com.sc.scbatis.demo;

import com.sc.scbatis.config.Configuration;
import com.sc.scbatis.config.MapperRegister;
import com.sc.scbatis.session.SqlSession;

/**
 * @author: shichao
 * @date: 2019/4/10
 * @description: 测试入口
 */
public class RunTest {


    public static void main(String[] args) {

        MapperRegister mapperRegister = new MapperRegister();
        mapperRegister.register("com.sc.scbatis.demo.TestMapper.getById", "select * from user where id = ?", User.class);
        Configuration configuration = new Configuration();
        configuration.setMapperRegister(mapperRegister);


        SqlSession sqlSession = new SqlSession(configuration);

        TestMapper testMapper =  sqlSession.getMapper(TestMapper.class);
        User user = testMapper.getById(3);
        System.out.println(user);
    }

}
