package com.system.beans;

/**
 * @author nanfang
 * @create 2021/01/11/14:35
 */
public class OrderDetail {
    private int orderId;
    private int dishId;

    public OrderDetail(int orderId, int dishId) {
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public OrderDetail() {
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
