package app.entities;

import java.util.Objects;

public class CupcakeBottom {
    private int bottomId;
    private String bottomName;
    private int bottomPrice;

    public CupcakeBottom(int bottomId, String bottomName, int bottomPrice) {
        this.bottomId = bottomId;
        this.bottomName = bottomName;
        this.bottomPrice = bottomPrice;
    }

    public int getBottomId() {
        return bottomId;
    }

    public String getBottomName() {
        return bottomName;
    }

    public int getBottomPrice() {
        return bottomPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CupcakeBottom that = (CupcakeBottom) o;
        return bottomId == that.bottomId && bottomPrice == that.bottomPrice && Objects.equals(bottomName, that.bottomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottomId, bottomName, bottomPrice);
    }

    @Override
    public String toString() {
        return "CupcakeBottom{" +
                "bottomId=" + bottomId +
                ", bottomName='" + bottomName + '\'' +
                ", bottomPrice=" + bottomPrice +
                '}';
    }
}
