package app.entities;

import java.util.Objects;

public class CupcakeBottom {
    private int bottom_id;
    private String bottomName;
    private int bottomPrice;

    public CupcakeBottom(int bottom_id, String bottomName, int bottomPrice) {
        this.bottom_id = bottom_id;
        this.bottomName = bottomName;
        this.bottomPrice = bottomPrice;
    }

    public int getBottom_id() {
        return bottom_id;
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
        return bottom_id == that.bottom_id && bottomPrice == that.bottomPrice && Objects.equals(bottomName, that.bottomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottom_id, bottomName, bottomPrice);
    }

    @Override
    public String toString() {
        return "CupcakeBottom{" +
                "bottom_id=" + bottom_id +
                ", bottomName='" + bottomName + '\'' +
                ", bottomPrice=" + bottomPrice +
                '}';
    }
}
