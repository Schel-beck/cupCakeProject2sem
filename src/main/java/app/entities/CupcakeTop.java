package app.entities;

import java.util.Objects;

public class CupcakeTop {

    private int topId;
    private String topName;
    private int topPrice;

    public CupcakeTop(int topId, String topName, int topPrice) {
        this.topId = topId;
        this.topName = topName;
        this.topPrice = topPrice;
    }

    public int getTopId() {
        return topId;
    }

    public String getTopName() {
        return topName;
    }

    public int getTopPrice() {
        return topPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CupcakeTop that = (CupcakeTop) o;
        return topId == that.topId &&
                topPrice == that.topPrice &&
                Objects.equals(topName, that.topName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topId, topName, topPrice);
    }

    @Override
    public String toString() {
        return "CupcakeTop{" +
                "topId=" + topId +
                ", topName='" + topName + '\'' +
                ", topPrice=" + topPrice +
                '}';
    }
}
