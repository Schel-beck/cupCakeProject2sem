package app.entities;

import java.util.Objects;

public class CupcakeTop {
    private int top_id;
    private String topName;
    private int topPrice;

    public CupcakeTop(int top_id, String topName, int topPrice) {
        this.top_id = top_id;
        this.topName = topName;
        this.topPrice = topPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CupcakeTop that = (CupcakeTop) o;
        return top_id == that.top_id && topPrice == that.topPrice && Objects.equals(topName, that.topName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(top_id, topName, topPrice);
    }

    @Override
    public String toString() {
        return "CupcakeTop{" +
                "top_id=" + top_id +
                ", topName='" + topName + '\'' +
                ", topPrice=" + topPrice +
                '}';
    }
}
