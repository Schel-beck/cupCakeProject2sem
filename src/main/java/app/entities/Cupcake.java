package app.entities;

public class Cupcake {
    private CupcakeBottom bottom;
    private CupcakeTop top;
    private int id;

    public Cupcake(int id, CupcakeBottom bottom, CupcakeTop top,){
        this.bottom = bottom;
        this.top = top;
        this.id;
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


}
