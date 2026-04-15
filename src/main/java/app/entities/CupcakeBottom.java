package app.entities;

import java.util.Objects;

public class CupcakeBottom {
    private int bottom_id;
    private String bottom_name;
    private int bottom_price;

    public CupcakeBottom(int bottom_id, String bottom_name, int bottom_price) {
        this.bottom_id = bottom_id;
        this.bottom_name = bottom_name;
        this.bottom_price = bottom_price;
    }

    public int getBottomid() {
        return bottom_id;
    }

    public String getBottomName() {
        return bottom_name;
    }

    public int getBottomPrice() {
        return bottom_price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CupcakeBottom that = (CupcakeBottom) o;
        return bottom_id == that.bottom_id && bottom_price == that.bottom_price && Objects.equals(bottom_name, that.bottom_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottom_id, bottom_name, bottom_price);
    }

    @Override
    public String toString() {
        return "CupcakeBottom{" +
                "bottom_id=" + bottom_id +
                ", bottomName='" + bottom_name + '\'' +
                ", bottomPrice=" + bottom_price +
                '}';
    }
}
