package com.lgh.springboot.esjdbc.esjdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ：lgh
 * @date ：Created in 2019/1/30 16:07
 * @description：jdbc
 * @modified By：
 */
public class MyJdbcTest {
    /**
     * 按条件查询
     */
    @Test
    public void getSqlByname() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = jdbcUtil.getConnect();

            //2.准备预编译的sql
            String sql = "SELECT * FROM  jsdz_report_video where fileName=?";

            //3.预编译
            stmt = conn.prepareStatement(sql);
            //注意条件需要使用相应的数类型接收
            stmt.setString(1,"0000002_000002_20190130154459_0000.txt");
            //4.执行sql
            rs = stmt.executeQuery();

            //5.遍历rs
            while (rs.next()) {
                String fileName = rs.getString("fileName");
                System.out.println(fileName);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            jdbcUtil.close(conn, stmt, rs);
        }
    }

    /**
     * 查询所有数据并且排序
     */
    @Test
    public void getByAllOrder(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = jdbcUtil.getConnect();

            //2.准备预编译的sql
            String sql = "select * from jsdz_report_video group by orgCode";

            //3.预编译
            stmt = conn.prepareStatement(sql);
            //4.执行sql
            rs = stmt.executeQuery();

            //5.遍历rs
            while (rs.next()) {
                int num = rs.getInt("num");
                String orgCode=rs.getString("orgCode");
                System.out.println("数量"+num+"单位"+orgCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            jdbcUtil.close(conn, stmt, rs);
        }
    }

    /**
     * 计算求和分组排序
     */
    @Test
    public void getcount(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = jdbcUtil.getConnect();

            //2.准备预编译的sql
            String sql = "SELECT count(*) counts FROM  jsdz_report_video";

            //3.预编译
            stmt = conn.prepareStatement(sql);
            //4.执行sql
            rs = stmt.executeQuery();

            //5.遍历rs
            while (rs.next()) {
                int sumtom=rs.getInt("counts");
                System.out.println(sumtom);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            jdbcUtil.close(conn, stmt, rs);
        }
    }
    /**
     * 限制查询数量
     */
    @Test
    public void getlimit(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = jdbcUtil.getConnect();

            //2.准备预编译的sql
            String sql = "SELECT * FROM  jsdz_report_video limit 2";

            //3.预编译
            stmt = conn.prepareStatement(sql);
            //4.执行sql
            rs = stmt.executeQuery();

            //5.遍历rs
            while (rs.next()) {
                String orgCode = rs.getString("orgCode");
                System.out.println(orgCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            jdbcUtil.close(conn, stmt, rs);
        }
    }
}
