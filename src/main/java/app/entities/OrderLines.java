package app.entities;

import java.util.Objects;

public class OrderLines {
    private int orderLineId;
    private int orderId;
    private CupcakeTop cupcakeTop;
    private CupcakeBottom cupcakeBottom;
    private int quantity;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderLines that = (OrderLines) o;
        return orderLineId == that.orderLineId && orderId == that.orderId && quantity == that.quantity && Objects.equals(cupcakeTop, that.cupcakeTop) && Objects.equals(cupcakeBottom, that.cupcakeBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderLineId, orderId, cupcakeTop, cupcakeBottom, quantity);
    }

    public OrderLines(int orderLineId, int orderId, CupcakeBottom cupcakeBottom, CupcakeTop cupcakeTop, int quantity, int price) {
        this.quantity = quantity;
        this.cupcakeBottom = cupcakeBottom;
        this.cupcakeTop = cupcakeTop;
        this.orderId = orderId;
        this.orderLineId = orderLineId;
        this.price = price;
    }

    public OrderLines(CupcakeBottom cupcakeBottom, CupcakeTop cupcakeTop, int quantity) {
        this.cupcakeTop = cupcakeTop;
        this.cupcakeBottom = cupcakeBottom;
        this.quantity = quantity;
        this.price = cupcakeBottom.getBottomPrice() + cupcakeTop.getTopPrice() * quantity;
    }

    @Override
    public String toString() {
        return "OrderLines{" +
                "orderLineId=" + orderLineId +
                ", orderId=" + orderId +
                ", cupcakeTop=" + cupcakeTop +
                ", cupcakeBottom=" + cupcakeBottom +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public CupcakeTop getCupcakeTop() {
        return cupcakeTop;
    }

    public CupcakeBottom getCupcakeBottom() {
        return cupcakeBottom;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}

