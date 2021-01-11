package com.system.beans;

/**
 * @author nanfang
 * @create 2021/01/11/14:17
 */
public class Dish {
    private int id;
    private String name;
    private String type;
    private float price;
    private String score;//评分
    private String comment;
    private String url;
    public Dish() {
    }

    public Dish(int id, String name, String type, float price, String score, String comment, String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.score = score;
        this.comment = comment;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", score='" + score + '\'' +
                ", comment='" + comment + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
