package com.lgh.springboot.esjdbc.esjdbc;

import java.sql.*;

/**
 * @author ：lgh
 * @date ：Created in 2019/1/30 14:43
 * @description：jdbc工具类 数据库连接和资源释放
 * @modified By：
 */
public class jdbcUtil {
    private static String url="jdbc:es://http://192.168.1.201:9200/?timezone=UTC";
    private static String user=null;
    private static String password=null;
    /**
     * 静态代码块(只加载一次)
     */
    static {
        try {
            Class.forName("org.elasticsearch.xpack.sql.jdbc.jdbc.JdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动程序注册出错");
        }

    }
    /**
     * 抽取获取连接对象的方法
     */
    public static Connection getConnect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源的方法
     */
    public static void close(Connection conn, Statement stmt){
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }


    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
