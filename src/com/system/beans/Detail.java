package com.system.beans;

/**
 * @author nanfang
 * @create 2021/01/11/14:35
 */
public class Detail {
    private int orderId;
    private int dishId;

    public Detail(int orderId, int dishId) {
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public Detail() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
