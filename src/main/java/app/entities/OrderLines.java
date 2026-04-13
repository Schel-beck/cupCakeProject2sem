package app.entities;

public class OrderLines {
    private int orderLineId;
    private int orderId;
    private CupcakeTop cupcakeTop;
    private CupcakeBottom cupcakeBottom;
    private int quantity;

    public OrderLines(int orderLineId, int orderId, CupcakeBottom cupcakeBottom, CupcakeTop cupcakeTop,   int quantity, int price) {
        this.quantity = quantity;
        this.cupcakeBottom = cupcakeBottom;
        this.cupcakeTop = cupcakeTop;
        this.orderId = orderId;
        this.orderLineId = orderLineId;
    }
}

