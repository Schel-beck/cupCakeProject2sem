package app.entities;

public class Orders {
    private int orderId;
    private int userId;
    private int orderDate;

    public Orders(int orderId, int userId, int orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;

    }
}
