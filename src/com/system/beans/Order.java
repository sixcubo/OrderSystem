package com.system.beans;

import javax.xml.crypto.Data;

/**
 * @author nanfang
 * @create 2021/01/11/14:25
 */
public class Order {
    private int id;
    private int tableNum;
    private String state;
    private Data dateTime;
    private String person;
    private String price;
    private int userId;

    public Order() {

    }

    public Order(int id, int tableNum, String state, Data dateTime, String person, String price, int userId) {
        this.id = id;
        this.tableNum = tableNum;
        this.state = state;
        this.dateTime = dateTime;
        this.person = person;
        this.price = price;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tableNum=" + tableNum +
                ", state='" + state + '\'' +
                ", dateTime=" + dateTime +
                ", person='" + person + '\'' +
                ", price='" + price + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Data getDateTime() {
        return dateTime;
    }

    public void setDateTime(Data dateTime) {
        this.dateTime = dateTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
