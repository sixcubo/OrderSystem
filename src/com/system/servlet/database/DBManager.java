package com.system.servlet.database;

import com.system.beans.*;

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

    public void initDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库初始化");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connectDB() {
        final String URL = "jdbc:mysql://121.4.121.91:3306/db_order_system?serverTimezone=GMT%2B8";
        final String USERNAME = "root";
        final String PASSWORD = "Songzhe_123";
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            conn.setAutoCommit(false);  // 关闭自动提交
            System.out.println("数据库连接");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("数据库连接关闭");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 全局userId
    private int globalUserId;

    public void setGlobalUserId(int globalUserId) {
        this.globalUserId = globalUserId;
    }

    public int getGlobalUserId() {
        return globalUserId;
    }

    /**
     * 查询所有 Dish
     *
     * @return -- ArrayList<Dish>
     */
    public ArrayList<Dish> selectAllDishes() {
        ArrayList<Dish> dishes = new ArrayList<>();

        String sql = "SELECT * FROM tb_dish";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

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
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dishes;
    }

    /**
     * 添加新的Dish
     *
     * @param dish -- 要插入的Dish对象, 不需要指定id
     * @return -- sql语句的执行结果, true/false
     */
    public boolean insertDish(Dish dish) {
        boolean isSuccess = false;

//        int id = dish.getId();
        String name = dish.getName();
        String type = dish.getType();
        double price = dish.getPrice();
        double score = dish.getScore();
        String comment = dish.getComment();
        String url = dish.getUrl();

        PreparedStatement ptmt = null;
        String sql = "INSERT INTO tb_dish(name, type, price, score, comment, url) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ptmt = conn.prepareStatement(sql);

//            ptmt.setInt(1, id);
            ptmt.setString(1, name);
            ptmt.setString(2, type);
            ptmt.setDouble(3, price);
            ptmt.setDouble(4, score);
            ptmt.setString(5, comment);
            ptmt.setString(6, url);

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 修改Dish
     *
     * @param dish -- 修改后的Dish对象. 使用id作为标识.
     * @return -- sql语句的执行结果, true/false.
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
                "SET name=?, type=?, price=?, score=?, comment=?, url=? " +
                "WHERE id=?";
        try {
            ptmt = conn.prepareStatement(sql);

//            ptmt.setInt(1, id);
            ptmt.setString(1, name);
            ptmt.setString(2, type);
            ptmt.setDouble(3, price);
            ptmt.setDouble(4, score);
            ptmt.setString(5, comment);
            ptmt.setString(6, url);
            ptmt.setInt(7, id);

            System.out.println(ptmt.toString());

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 通过id删除Dish
     *
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 根据username查询User
     *
     * @param username -- ...
     * @return -- ...
     */
    public User selectUserByUsername(String username) {
        User user = null;

        String sql = "SELECT * FROM tb_user WHERE username='" + username + "'";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            if (res.next()) {
                int id = res.getInt("id");
                String password = res.getString("password");
                String tel = res.getString("tel");
                double money = res.getDouble("money");
                user = new User(id, username, password, tel, money);
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /**
     * 查询所有User
     *
     * @return -- ArrayList<User>
     */
    public ArrayList<User> selectAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT * FROM tb_user";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("id");
                String username = res.getString("username");
                String password = res.getString("password");
                String tel = res.getString("tel");
                double money = res.getDouble("money");

                User user = new User(id, username, password, tel, money);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    /**
     * 向tb_user表插入User
     *
     * @param user -- 要插入的User, 不需要指定id
     * @return -- sql语句的执行结果, true/false
     */
    public boolean insertUser(User user) {
        boolean isSuccess = false;

        String username = user.getUsername();
        String password = user.getPassword();
        String tel = user.getTel();
        double money = user.getMoney();

        PreparedStatement ptmt = null;
        String sql = "INSERT INTO tb_user(username, password, tel, money) " +
                "VALUES(?, ?, ?, ?)";
        try {
            ptmt = conn.prepareStatement(sql);

            ptmt.setString(1, username);
            ptmt.setString(2, password);
            ptmt.setString(3, tel);
            ptmt.setDouble(4, money);

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 修改User
     *
     * @param user -- 修改后的User对象. 使用username作为标识.
     * @return -- sql语句的执行结果, true/false.
     */
    public boolean updateUser(User user) {
        boolean isSuccess = false;

//        int id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String tel = user.getTel();
        double money = user.getMoney();

        PreparedStatement ptmt = null;
        String sql = "UPDATE tb_user " +
                "SET password=?, tel=?, money=? " +
                "WHERE username=?";
        try {
            ptmt = conn.prepareStatement(sql);

            ptmt.setString(1, password);
            ptmt.setString(2, tel);
            ptmt.setDouble(3, money);
            ptmt.setString(4, username);

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 根据username删除User
     *
     * @param username -- ...
     * @return -- sql语句的执行结果, true/false
     */
    public boolean deleteUser(String username) {
        boolean isSuccess = false;

        String sql = "DELETE FROM tb_user WHERE username=?";
        PreparedStatement ptmt = null;
        try {
            ptmt = conn.prepareStatement(sql);

            ptmt.setString(1, username);

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 查询所有Order
     *
     * @return -- ArrayList<Order>
     */
    public ArrayList<Order> selectAllOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();

        String sql = "select * from tb_order";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("id");
                int tableNO = res.getInt("tableNO");
                String state = res.getString("state");

//                String time = res.getDate("time").toString();

                String time = "";
                Date _date = res.getDate("time");
                Time _time = res.getTime("time");
                if (_date != null && _time != null) {
                    time = _date.toString() + " " + _time.toString();
                }

                int personNum = res.getInt("person_num");
                double price = res.getDouble("price");
                int userId = res.getInt("user_id");
                int merchantId = res.getInt("merchant_id");

                Order order = new Order(id, tableNO, state, time, personNum, price, userId, merchantId);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }


    /**
     * 根据TableNO查询Order
     *
     * @param TableNO
     * @return
     */
    public Order selectOrderByTableNO(int TableNO) {
        Order order = null;

        String sql = "SELECT * FROM tb_order WHERE tableNO='" + TableNO + "'";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            if (res.next()) {
                int id = res.getInt("id");
                int tableNO = res.getInt("tableNO");
                String state = res.getString("state");

                String time = "";
                Date _date = res.getDate("time");
                Time _time = res.getTime("time");
                if (_date != null && _time != null) {
                    time = _date.toString() + " " + _time.toString();
                }

                int personNum = res.getInt("person_num");
                double price = res.getDouble("price");
                int userId = res.getInt("user_id");
                int merchantId = res.getInt("merchant_id");

                order = new Order(id, tableNO, state, time, personNum, price, userId, merchantId);
            } else {
                order = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }


    /**
     * 根据类型(type)查询对应的所有菜品(Dish)
     *
     * @param type -- 要查找的类型(type)
     * @return -- 此类型的所有菜品(Dish)
     */
    public ArrayList<Dish> selectDishesByType(String type) {
        ArrayList<Dish> dishes = new ArrayList<Dish>();

        String sql = "SELECT * FROM tb_dish WHERE type='" + type + "'";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
//                String type = res.getString("type");
                double price = res.getDouble("price");
                double score = res.getDouble("score");
                String comment = res.getString("comment");
                String url = res.getString("url");

                Dish dish = new Dish(id, name, type, price, score, comment, url);
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dishes;
    }

    /**
     * 根据客人用户名(username)查找所有订单(Order)
     *
     * @param username -- 要查找的客人的用户名(username)
     * @return -- 此客人的所有订单(Order)
     */
    public ArrayList<Order> selectOrdersByUsername(String username) {
        ArrayList<Order> orders = new ArrayList<>();

        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();

            String sql0 = "SELECT id FROM tb_user WHERE username='" + username + "'";
            res = stmt.executeQuery(sql0);
            res.next();
            int id = res.getInt("id");

            String sql1 = "SELECT * FROM tb_order WHERE user_id='" + id + "'";
            res = stmt.executeQuery(sql1);
            while (res.next()) {
//                int id = res.getInt("id");
                int tableNO = res.getInt("tableNO");
                String state = res.getString("state");

                String time = "";
                Date _date = res.getDate("time");
                Time _time = res.getTime("time");
                if (_date != null && _time != null) {
                    time = _date.toString() + " " + _time.toString();
                }

                int personNum = res.getInt("person_num");
                double price = res.getDouble("price");
                int userId = res.getInt("user_id");
                int merchantId = res.getInt("merchant_id");

                Order order = new Order(id, tableNO, state, time, personNum, price, userId, merchantId);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    /**
     * 使用部分菜名(subName)模糊搜索菜品(Dish)
     *
     * @param subName -- 部分菜名
     * @return -- 菜名中包含 subName 的所有菜品(Dish)
     */
    public ArrayList<Dish> selectDishesBySubName(String subName) {
        ArrayList<Dish> dishes = new ArrayList<>();

        String sql = "SELECT * FROM tb_dish WHERE name like '%" + subName + "%'";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();

            res = stmt.executeQuery(sql);
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
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dishes;
    }

    /**
     * 根据桌号查找桌信息
     *
     * @param tableNO
     * @return
     */
    public Table selectTableByTableNO(int tableNO) {
        Table table = null;

        String sql = "SELECT * FROM tb_table WHERE tableNO=" + tableNO;
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            res.next();

            int id = res.getInt("id");
            int maxPersonNum = res.getInt("maxPersonNum");
            int personNum = res.getInt("personNum");
            String state = res.getString("state");
            table = new Table(id, tableNO, maxPersonNum, personNum, state);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return table;
    }

    /**
     * 查询所有桌信息
     *
     * @return
     */
    public ArrayList<Table> selectAllTables() {
        ArrayList<Table> tables = new ArrayList<>();

        String sql = "SELECT * FROM tb_table";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id");
                int tableNO = res.getInt("tableNO");
                int maxPersonNum = res.getInt("maxPersonNum");
                int personNum = res.getInt("personNum");
                String state = res.getString("state");
                Table table = new Table(id, tableNO, maxPersonNum, personNum, state);
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }

    /**
     * 根据菜的id查询菜品的详细信息
     *
     * @param id
     * @return
     */
    public Dish selectDishById(int id) {
        Dish dish = null;

        String sql = "SELECT * FROM tb_dish WHERE id=" + id;
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);

            res.next();
//            int id = res.getInt("id");
            String name = res.getString("name");
            String type = res.getString("type");
            double price = res.getDouble("price");
            double score = res.getDouble("score");
            String comment = res.getString("comment");
            String url = res.getString("url");

            dish = new Dish(id, name, type, price, score, comment, url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

    /**
     * 插入订单详情表(tb_order_detail), 同时更新订单表(tb_order)
     *
     * @param orderId
     * @param dishId
     * @return
     */
    public boolean insertOrderDetail(int orderId, int dishId) {
        boolean isSuccess = false;

        double orderPrice = this.selectPriceByOrderId(orderId);
        double dishPrice = this.selectPriceByDishId(dishId);

        String sql0 = "INSERT INTO tb_order_detail(order_id, dish_id) " +
                "VALUES('" + orderId + "', '" + dishId + "')";
        String sql1 = "UPDATE tb_order SET price=" + (orderPrice + dishPrice) +
                " WHERE id=" + orderId;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            int rows0 = stmt.executeUpdate(sql0);
            int rows1 = stmt.executeUpdate(sql1);

            if (rows0 == 1 && rows1 == 1) {
                isSuccess = true;
//                conn.commit();
            }
//            else {
//                throw java.sql.SQLException;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 已选订单中删除菜的操作, 同时更新订单表(tb_order)
     *
     * @param orderId
     * @param dishId
     * @return
     */
    public boolean deleteOrderDetail(int orderId, int dishId) {
        boolean isSuccess = false;

        double orderPrice = this.selectPriceByOrderId(orderId);
        double dishPrice = this.selectPriceByDishId(dishId);

        String sql0 = "DELETE FROM tb_order_detail " +
                "WHERE order_id=" + orderId + " AND dish_id=" + dishId + "";
        String sql1 = "UPDATE tb_order SET price=" + (orderPrice - dishPrice) +
                " WHERE id=" + orderId;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            int rows0 = stmt.executeUpdate(sql0);
            int rows1 = stmt.executeUpdate(sql1);

            if (rows0 == 1 && rows1 == 1) {
                isSuccess = true;
//                conn.commit();
            }
//            else {
//                throw java.sql.SQLException;
//            }
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 根据订单号，计算该订单所有菜的总价钱
     *
     * @param orderId
     * @return
     */
    public double selectMoneyByOrderId(int orderId) {
        double sum = 0;

        String sql = "SELECT price FROM tb_order WHERE id=" + orderId;
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            res.next();
            sum = res.getDouble("price");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    /**
     * 根据商家账号查找密码
     *
     * @param username
     * @return
     */
    public String selectMerchantPassword(String username) {
        String password = "";

        String sql = "SELECT password FROM tb_merchant WHERE username='" + username + "'";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            res.next();
            password = res.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return password;
    }

    /**
     * 修改商家信息
     *
     * @param merchant
     * @return
     */
    public boolean updateMerchant(Merchant merchant) {
        boolean isSuccess = false;

        String username = merchant.getUsername();
        String password = merchant.getPassword();
        int id = merchant.getId();

        String sql = "UPDATE tb_merchant " +
                "SET username='" + username + "', password='" + password + "'" +
                "WHERE id=" + id;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 查询订单的所有菜品
     *
     * @param order
     * @return
     */
    public ArrayList<Dish> selectDishesByOrder(Order order) {
        ArrayList<Dish> dishes = new ArrayList<Dish>();

        int orderId = order.getId();
        String sql = "SELECT * FROM tb_dish, tb_order_detail " +
                "WHERE tb_order_detail.order_id=" + orderId +
                " AND tb_order_detail.dish_id=tb_dish.id";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
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
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dishes;
    }

    /**
     * 查找数据库中所有状态为state餐桌
     *
     * @param state
     * @return
     */
    public ArrayList<Table> selectTablesByState(String state) {
        ArrayList<Table> tables = new ArrayList<>();

        String sql = "SELECT * FROM tb_table WHERE state='" + state + "'";
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id");
                int tableNO = res.getInt("tableNO");
                int maxPersonNum = res.getInt("maxPersonNum");
                int personNum = res.getInt("personNum");
//                String state = res.getString("state");
                Table table = new Table(id, tableNO, maxPersonNum, personNum, state);
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }

    /**
     * 插入 Table 信息, id 无需指定
     *
     * @param table
     * @return
     */
    public boolean insertTable(Table table) {
        boolean isSuccess = false;

//        int id = table.getId();
        int tableNO = table.getTableNO();
        int maxPersonNum = table.getMaxPersonNum();
        int personNum = table.getPersonNum();
        String state = table.getState();

        PreparedStatement ptmt = null;
        String sql = "INSERT INTO tb_table(tableNO, maxPersonNum, personNum, state) " +
                "VALUES(?, ?, ?, ?)";
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, tableNO);
            ptmt.setInt(2, maxPersonNum);
            ptmt.setInt(3, personNum);
            ptmt.setString(4, state);
            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 从菜品表(tb_dish)中查询价格
     *
     * @param dishId -- 菜品id
     * @return
     */
    public double selectPriceByDishId(int dishId) {
        double price = 0;

        String sql = "SELECT price FROM tb_dish WHERE id=" + dishId;
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            res.next();
            price = res.getDouble("price");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return price;
    }

    /**
     * 从订单表(tb_order)中查询价格
     *
     * @param orderId -- 订单id
     * @return
     */
    public double selectPriceByOrderId(int orderId) {
        double price = 0;

        String sql = "SELECT price FROM tb_order WHERE id=" + orderId;
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            res.next();
            price = res.getDouble("price");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return price;
    }

//    /**
//     * 生成订单
//     *
//     * @param order -- Order对象无需指定 id, time, price 属性
//     * @return
//     */
//    public boolean insertOrder(Order order) {
//        boolean isSuccess = false;
//
////        int id = order.getId();
////        String time = order.getTime();
////        double price = order.getPrice();
////        int merchantId = order.getMerchantId();
//
//        int tableNO = order.getTableNO();
//        String state = order.getState();
//        int personNum = order.getPersonNum();
//        int userId = order.getUserId();
//
//        PreparedStatement ptmt = null;
//        String sql = "INSERT INTO tb_order(tableNO, state, time, person_num, user_id) " +
//                "VALUES(?, ?, NOW(), ?, ?)";
//        try {
//            ptmt = conn.prepareStatement(sql);
//            ptmt.setInt(1, tableNO);
//            ptmt.setString(2, state);
//            ptmt.setInt(3, personNum);
//            ptmt.setInt(4, userId);
//            int rows = ptmt.executeUpdate();
//            if (rows == 1) {
//                isSuccess = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return isSuccess;
//    }

    /**
     * 生成订单
     *
     * @return
     */
    public boolean insertOrder() {
        boolean isSuccess = false;

        Statement stmt = null;
        String sql = "INSERT INTO tb_order(time) VALUES(NOW())";
        try {
            stmt = conn.createStatement();
            int rows = stmt.executeUpdate(sql);
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    // 根据订单id, 查订单
    public Order selectOrderById(int id) {
        Order order = null;

        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tb_order WHERE id=" + id;
            res = stmt.executeQuery(sql);
            res.next();

            int tableNO = res.getInt("tableNO");
            String state = res.getString("state");

            String time = "";
            Date _date = res.getDate("time");
            Time _time = res.getTime("time");
            if (_date != null && _time != null) {
                time = _date.toString() + " " + _time.toString();
            }

            int personNum = res.getInt("person_num");
            double price = res.getDouble("price");
            int userId = res.getInt("user_id");
            int merchantId = res.getInt("merchant_id");

            order = new Order(id, tableNO, state, time, personNum, price, userId, merchantId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // 查某状态的所有订单
    public ArrayList<Order> selectOrdersByState(String state) {
        ArrayList<Order> orders = new ArrayList<>();

        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();

            String sql = "SELECT * FROM tb_order WHERE state='" + state + "'";
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id");
                int tableNO = res.getInt("tableNO");
//                String state = res.getString("state");

                String time = "";
                Date _date = res.getDate("time");
                Time _time = res.getTime("time");
                if (_date != null && _time != null) {
                    time = _date.toString() + " " + _time.toString();
                }

                int personNum = res.getInt("person_num");
                double price = res.getDouble("price");
                int userId = res.getInt("user_id");
                int merchantId = res.getInt("merchant_id");

                Order order = new Order(id, tableNO, state, time, personNum, price, userId, merchantId);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    /**
     * @param order -- 无须指定time, merchantId
     * @return
     */
    public boolean updateOrder(Order order) {
        boolean isSuccess = false;

        int id = order.getId();
        int tableNO = order.getTableNO();
        String state = order.getState();
//        String time = order.getTime();
        int personNum = order.getPersonNum();
        double price = order.getPrice();
        int userId = order.getUserId();

        PreparedStatement ptmt = null;
        String sql = "UPDATE tb_order " +
                "SET tableNO=?, state=?, person_num=?, price=?, user_id=? " +
                "WHERE id=?";
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, tableNO);
            ptmt.setString(2, state);
//            ptmt.setString(3, time);
            ptmt.setInt(3, personNum);
            ptmt.setDouble(4, price);
            ptmt.setInt(5, userId);
            ptmt.setInt(6, id);

            int rows = ptmt.executeUpdate();
            if (rows == 1) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 根据userId查询相应订单
     *
     * @param userId
     * @return
     */
    public ArrayList<Order> seleteOrdersByUserId(int userId) {
        ArrayList<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM tb_order WHERE user_id=" + userId;
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id");
                int tableNO = res.getInt("tableNO");
                String state = res.getString("state");

                String time = "";
                Date _date = res.getDate("time");
                Time _time = res.getTime("time");
                if (_date != null && _time != null) {
                    time = _date.toString() + " " + _time.toString();
                }

                int personNum = res.getInt("person_num");
                double price = res.getDouble("price");
//                int userId = res.getInt("user_id");
                int merchantId = res.getInt("merchant_id");

                Order order = new Order(id, tableNO, state, time, personNum, price, userId, merchantId);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }


    public static void main(String[] args) {
        DBManager.getInst().initDB();
        DBManager.getInst().connectDB();


//        ArrayList<Order> orders = DBManager.getInst().seleteOrdersByUserId(10);
//        for (Order o : orders) {
//            System.out.println(o);
//        }

//        Order order = new Order(9, 10, "1", "NOW()", 2, 72, 10, 0);
//        System.out.println(DBManager.getInst().updateOrder(order));

//        ArrayList<Order> orders = DBManager.getInst().selectOrdersByState("未付款");
//        for (Order o : orders) {
//            System.out.println(o);
//        }

//        System.out.println(DBManager.getInst().selectOrderById(9));

//        System.out.println(DBManager.getInst().deleteOrderDetail(5, 20));


//        System.out.println(DBManager.getInst().insertOrder());

//        System.out.println(DBManager.getInst().selectPriceByOrderId(1));

//        Table table = new Table(0, 11, 8, 0, "state");
//        System.out.println(DBManager.getInst().insertTable(table));

//        ArrayList<Table> tables = DBManager.getInst().selectTablesByState("空闲");
//        for (Table t : tables) {
//            System.out.println(t);
//        }

//        Order order = new Order();
//        order.setId(1);
//        ArrayList<Dish> dishes = DBManager.getInst().selectDishesByOrder(order);
//        for (Dish d : dishes) {
//            System.out.println(d);
//        }

//        Merchant merchant = new Merchant(2, "cxhh", "cxhh");
//        System.out.println(DBManager.getInst().updateMerchant(merchant));

//        System.out.println(DBManager.getInst().selectMerchantPassword("root"));

//        System.out.println(DBManager.getInst().selectMoneyByOrderId(1));

//        System.out.println(DBManager.getInst().deleteOrderDetail(2, 2));

//        System.out.println(DBManager.getInst().insertOrderDetail(5, 20));

//        System.out.println(DBManager.getInst().selectDishById(5));

//        ArrayList<Table> tables = DBManager.getInst().selectAllTables();
//        for (Table t : tables) {
//            System.out.println(t);
//        }

//        System.out.println(DBManager.getInst().selectTableByTableNO(5));

//        ArrayList<Dish> dishes = DBManager.getInst().selectDishesBySubName("n");
//        for (Dish d : dishes) {
//            System.out.println(d);
//        }

//        ArrayList<Dish> dishes = DBManager.getInst().selectDishesByType("type");
//        for (Dish d : dishes) {
//            System.out.println(d);
//        }

//        ArrayList<Dish> dishes = DBManager.getInst().selectAllDishes();
//        for (Dish d : dishes) {
//            System.out.println(d);
//        }

//
//        Dish dish = new Dish(5, "name8", "type", 5, 5, "comment", "url");
//        System.out.println(DBManager.getInst().updateDish(dish));


//        Dish dish = new Dish(9, "name7", "type", 5, 5, "comment", "url");
//        System.out.println(DBManager.getInst().insertDish(dish));


//        System.out.println(DBManager.getInst().deleteDishById(9));


//        System.out.println(DBManager.getInst().selectUserByUsername("cxh"));


//        ArrayList<User> users = DBManager.getInst().selectAllUsers();
//        for (User u : users) {
//            System.out.println(u);
//        }

//
//        User user = new User(0, "zhch", "zhch", "10010", 10000000);
//        System.out.println(DBManager.getInst().insertUser(user));
//
//        User user = new User(6, "zhch", "zhch", "1001000", 10000000);
//        System.out.println(DBManager.getInst().updateUser(user));


//        System.out.println(DBManager.getInst().deleteUser("gft"));


//        ArrayList<Order> orders = DBManager.getInst().selectAllOrders();
//        for (Order o : orders) {
//            System.out.println(o);
//        }


        ArrayList<Order> orders = DBManager.getInst().selectOrdersByUsername("zhangchao");
        for (Order o : orders) {
            System.out.println(o);
        }


//        System.out.println(DBManager.getInst().selectOrderByTableNO(10));


//        DBManager.getInst().closeDB();


    }
}
