package com.sc.scbatis.executor;

import com.sc.scbatis.db.DataBaseUtil;
import com.sc.scbatis.demo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: shichao
 * @date: 2019/4/9
 * @description: 默认的Excutor
 */
public class DefaultExecutor implements Executor {


    public <T> T selectOne(String statement, Object parameter) throws Exception {
        Connection conn = DataBaseUtil.getConnection();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(statement);
        pstmt.setInt(1,Integer.parseInt(String.valueOf(parameter)));
        ResultSet rs = pstmt.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setGender(rs.getInt("gender"));
        }
        return (T) user;

    }
}
