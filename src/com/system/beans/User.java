package com.system.beans;

/**
 * @author nanfang
 * @create 2021/01/11/14:14
 */
public class User {
    private int id;
    private String account;
    private String password;
    private String tel;
    private String email;

    public User() {
    }

    public User(int id, String account, String password, String tel, String email) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
