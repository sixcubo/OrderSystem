package com.system.servlet.database;

import java.sql.*;


/**
 * @author 666
 * @create 2021-01-11 15:05:53 +0800
 */
public class DBManager {

    // 使构造函数为 private，避免类被实例化
    private DBManager() {
    }
    private static DBManager instance = new DBManager(); // 单例
    // 获取单例
    public static DBManager getInst() {
        return instance;
    }

    private Connection conn = null;
    private Statement stat = null;
//    private PreparedStatement preStat = null;
//    private CallableStatement cstm = null;

    private void initDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void connectDB() {
        final String URL = "jdbc:mysql://121.4.121.91:3306/db_order_system?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "Songzhe_123";

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stat = conn.createStatement();

//            ResultSet res = null;
//            res = stat.executeQuery("select * from tb_dish");
//
//            while (res.next()) {
//                int _id = res.getInt("dish_id");
//                System.out.println(_id);
//            }
//            res.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeDB() {
        try {
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




//    public static void main(String[] args) {
//        DBManager.getInst().initDB();
//        DBManager.getInst().connectDB();
//    }
}
