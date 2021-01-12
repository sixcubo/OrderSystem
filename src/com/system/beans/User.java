package com.system.beans;

/**
 * @author nanfang
 * @create 2021/01/11/14:14
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String tel;
    private double money;

    public User() {
    }

    public User(int id, String username, String password, String tel, double money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", money=" + money +
                '}';
    }
}
