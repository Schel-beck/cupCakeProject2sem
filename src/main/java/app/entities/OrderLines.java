package app.entities;

public class OrderLines {
    private int orderLineId;
    private int orderId;
    private CupcakeTop cupcakeTop;
    private CupcakeBottom cupcakeBottom;
    private int quantity;

    public OrderLines(int quantity, CupcakeBottom cupcakeBottom, CupcakeTop cupcakeTop, int orderId, int orderLineId) {
        this.quantity = quantity;
        this.cupcakeBottom = cupcakeBottom;
        this.cupcakeTop = cupcakeTop;
        this.orderId = orderId;
        this.orderLineId = orderLineId;
    }
}

