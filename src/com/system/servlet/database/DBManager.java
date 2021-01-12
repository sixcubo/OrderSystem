package com.system.servlet.database;

import com.system.beans.Dish;
import com.system.beans.Order;

import javax.net.ssl.CertPathTrustManagerParameters;
import java.sql.*;
import java.util.ArrayList;


/**
 * @author 666
 * @create 2021-01-11 15:05:53 +0800
 */
public class DBManager {

    // 使构造函数为 private，避免类被实例化
    private DBManager() {
    }

    private static DBManager instance = new DBManager(); // 单例

    public static DBManager getInst() {
        return instance;
    } // 获取单例

    private Connection conn = null;
    private Statement stmt = null;

    public void initDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connectDB() {
        final String URL = "jdbc:mysql://121.4.121.91:3306/db_order_system?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "Songzhe_123";

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();

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

    public void closeDB() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 全局商家id
    private String merchantId = null;

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 查询所有 Dish
     *
     * @return -- ArrayList<Dish>
     */
    public ArrayList<Dish> selectAllDishes() {
        ArrayList<Dish> dishes = null;
        String sql = "SELECT * FROM tb_dish";

        try {
            ResultSet res = this.stmt.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String type = res.getString("type");
                double price = res.getDouble("price");
                double score = res.getDouble("score");
                String comment = res.getString("comment");
                String url = res.getString("url");

                Dish dish = new Dish(id, name, type, price, score, comment, url);
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dishes;
    }

    /**
     * 添加新的Dish
     *
     * @param dish -- 要插入的Dish对象
     * @return -- sql语句的执行结果, true/false
     */
    public boolean insertDish(Dish dish) {
        boolean isSuccess = false;

        int id = dish.getId();
        String name = dish.getName();
        String type = dish.getType();
        double price = dish.getPrice();
        double score = dish.getScore();
        String comment = dish.getComment();
        String url = dish.getUrl();

        PreparedStatement ptmt = null;
        String sql = "INSERT INTO tb_dish(id, name, type, price, score, comment, url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ptmt = conn.prepareStatement(sql);

            ptmt.setInt(1, id);
            ptmt.setString(2, name);
            ptmt.setString(3, type);
            ptmt.setDouble(4, price);
            ptmt.setDouble(5, score);
            ptmt.setString(6, comment);
            ptmt.setString(7, url);

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ptmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 修改Dish
     *
     * @param dish -- 修改后的Dish对象, 确保id始终不会被修改
     * @return -- sql语句的执行结果, true/false
     */
    public boolean updateDish(Dish dish) {
        boolean isSuccess = false;

        int id = dish.getId();
        String name = dish.getName();
        String type = dish.getType();
        double price = dish.getPrice();
        double score = dish.getScore();
        String comment = dish.getComment();
        String url = dish.getUrl();

        PreparedStatement ptmt = null;
        String sql = "UPDATE tb_dish " +
                "SET id=?, name=?, type=?, price=?, score=?, comment=?, url=? " +
                "WHERE id=?";
        try {
            ptmt = conn.prepareStatement(sql);

            ptmt.setInt(1, id);
            ptmt.setString(2, name);
            ptmt.setString(3, type);
            ptmt.setDouble(4, price);
            ptmt.setDouble(5, score);
            ptmt.setString(6, comment);
            ptmt.setString(7, url);
            ptmt.setInt(8, id);

            System.out.println(ptmt.toString());

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ptmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 通过id删除Dish
     * @param id -- 要删除的Dish的id
     * @return -- sql语句的执行结果, true/false
     */
    public boolean deleteDishById(int id) {
        boolean isSuccess = false;

        String sql = "DELETE FROM tb_dish WHERE id=?";
        PreparedStatement ptmt = null;

        try {
            ptmt = conn.prepareStatement(sql);

            ptmt.setInt(1, id);

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccess;
    }

//    public User selectUserByUsername(String username) {
//
//    }
//
//    public ArrayList<User> selectAllUser() {
//
//    }
//
//    public ArrayList<User> insertUser(User user) {
//
//    }
//
//    public boolean updateUser(User user) {
//    }
//
//    public boolean deleteUser(String username) {
//    }
//
//    public ArrayList<Order> selectAllOrder() {
//    }
//
//    public Order selectOrderByTable(int Table) {
//    }


    public static void main(String[] args) {
        DBManager.getInst().initDB();
        DBManager.getInst().connectDB();

//        Dish dish = new Dish(8, "name5", "type", 5, 5, "comment", "url");

        System.out.println(DBManager.getInst().deleteDishById(7));
//        DBManager.getInst().selectAllDish();
    }
}
