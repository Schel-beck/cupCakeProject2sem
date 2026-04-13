package app.entities;

public class Cupcake {
    private CupcakeBottom bottom;
    private CupcakeTop top;
    private int id;

    public Cupcake(int id, CupcakeBottom bottom, CupcakeTop top,){
        this.bottom = bottom;
        this.top = top;
        this.id = id;
    }
    public Cupcake(CupcakeBottom bottom, CupcakeTop top,){
        this.bottom = bottom;
        this.top = top;

    }

    public int getCupcakeBottom(){
        return bottom;
    }
    public  setCupcakeBottom(CupcakeBottom bottom){
        this.bottom;
    }


    public CupcakeBottom getBottom() {
        return bottom;
    }

    public void setBottom(CupcakeBottom bottom) {
        this.bottom = bottom;
    }

    public CupcakeTop getTop() {
        return top;
    }

    public void setTop(CupcakeTop top) {
        this.top = top;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
