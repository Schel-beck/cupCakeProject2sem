package app.entities;

import java.util.Objects;

public class Orders {
    private int orderId;
    private int userId;
    private String orderDate;

    public Orders(int orderId, int userId, String orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;

    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return orderId == orders.orderId && userId == orders.userId && Objects.equals(orderDate, orders.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, orderDate);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
