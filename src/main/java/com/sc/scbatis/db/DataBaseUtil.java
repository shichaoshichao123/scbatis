package com.sc.scbatis.db;

import java.sql.*;

/**
 * @author: shichao
 * @date: 2019/4/10
 * @description: 数据库链接工具
 */
public class DataBaseUtil {

    private DataBaseUtil() {
    }

    public static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/Test?characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            //classLoader,加载对应驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static Integer getAll() {
        Connection conn = getConnection();
        String sql = "select * from user";
        PreparedStatement pstmt;
        try {
            pstmt =  conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DataBaseUtil.getAll();
    }
}
