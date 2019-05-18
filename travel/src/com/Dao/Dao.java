package com.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;


    public Dao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载数据库驱动成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败！");
            e.printStackTrace();
        }
    }


    /**
     * 链接数据库
     */
    public Connection getCon() {
        try {
            con = DriverManager.getConnection
                    ("jdbc:mysql://120.77.241.216:3306/mysql?user=root&password=1234&useSSL=false&serverTimezone=UTC" );
            System.out.println("创建数据库连接成功！");
        } catch (SQLException e) {
            System.out.print(con);
            System.out.println("创建数据库连接失败！");
            con = null;
            e.printStackTrace();
        }
        return con;
    }
    public void closed() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭con对象失败！");
            e.printStackTrace();
        }
    }
}
