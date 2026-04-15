package app.entities;

import java.util.Objects;

public class CupcakeTop {
    private int top_id;
    private String top_name;
    private int top_price;

    public CupcakeTop(int top_id, String top_name, int top_price) {
        this.top_id = top_id;
        this.top_name = top_name;
        this.top_price = top_price;
    }

    public int getTopid() {
        return top_id;
    }

    public String getTopName() {
        return top_name;
    }

    public int getTopPrice() {
        return top_price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CupcakeTop that = (CupcakeTop) o;
        return top_id == that.top_id && top_price == that.top_price && Objects.equals(top_name, that.top_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top_id, top_name, top_price);
    }

    @Override
    public String toString() {
        return "CupcakeTop{" +
                "top_id=" + top_id +
                ", topName='" + top_name + '\'' +
                ", topPrice=" + top_price +
                '}';
    }
}
