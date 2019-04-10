package com.sc.scbatis.demo;

import com.sc.scbatis.session.SqlSession;

/**
 * @author: shichao
 * @date: 2019/4/10
 * @description: 测试入口
 */
public class RunTest {


    public static void main(String[] args) {
        //直接获取sqlSession
        SqlSession sqlSession = new SqlSession(null);
        //从session中回去指定的Mapper代理
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        //执行mapper接口方法
        User user = testMapper.getById(3);
        System.out.println(user);
    }

}
