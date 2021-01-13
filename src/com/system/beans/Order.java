package com.system.beans;

/**
 * @author nanfang
 * @create 2021/01/11/14:25
 */
public class Order {
    private int id;
    private int tableNO;
    private String state;
    private String time;
    private int personNum;
    private double price;
    private int userId;
    private int merchantId;


    public Order() {

    }

    public Order(int id, int tableNO, String state, String time, int personNum, double price, int userId, int merchantId) {
        this.id = id;
        this.tableNO = tableNO;
        this.state = state;
        this.time = time;
        this.personNum = personNum;
        this.price = price;
        this.userId = userId;
        this.merchantId = merchantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNO() {
        return tableNO;
    }

    public void setTableNO(int tableNO) {
        this.tableNO = tableNO;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tableNO=" + tableNO +
                ", state='" + state + '\'' +
                ", time='" + time + '\'' +
                ", personNum=" + personNum +
                ", price=" + price +
                ", userId=" + userId +
                ", merchantId=" + merchantId +
                '}';
    }
}
