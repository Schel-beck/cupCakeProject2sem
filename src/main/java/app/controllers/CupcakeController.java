package app.controllers;

import app.entities.CupcakeBottom;
import app.entities.CupcakeTop;
import app.exception.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;


public class CupcakeController {

public static void addRoutes(Javalin app){

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    app.post("/orders", ctx -> {getAllBottoms(ctx, connectionPool);
    getAllTops(ctx, connectionPool);});
    app.get("/orders", ctx -> loadOrderPage(ctx, connectionPool));
}



    private static void getAllBottoms(Context ctx, ConnectionPool connectionPool){

        List<CupcakeBottom> allBottoms = null;

        try{
            CupcakeMapper cupcakeMapper = new CupcakeMapper();
            allBottoms =cupcakeMapper.getAllCupcakeBottoms(connectionPool);
            ctx.sessionAttribute("bottom_options", allBottoms);
            ctx.sessionAttribute("bottom_options", allBottoms);
            ctx.redirect("/orders");
            ctx.render("orders.html");

        }
        catch(DatabaseException e){
            System.out.println(e.getMessage());
            ctx.redirect("/");
        }

    }

    private static void getAllTops(Context ctx, ConnectionPool connectionPool){

        List<CupcakeTop> allTops = null;

        try{
            CupcakeMapper cupcakeMapper = new CupcakeMapper();
            allTops = cupcakeMapper.getAllCupcakeTops(connectionPool);
            ctx.sessionAttribute("top_options", allTops);
            ctx.sessionAttribute("top_options", allTops);
            ctx.render("order-page.html");

        }
        catch(DatabaseException e){
            System.out.println(e.getMessage());
            ctx.redirect("/");
        }

    }

    private static void loadOrderPage(Context ctx, ConnectionPool connectionPool) {

        try {
            CupcakeMapper cupcakeMapper = new CupcakeMapper();

            List<CupcakeBottom> bottoms = cupcakeMapper.getAllCupcakeBottoms(connectionPool);
            List<CupcakeTop> tops = cupcakeMapper.getAllCupcakeTops(connectionPool);

            ctx.attribute("bottom_options", bottoms);
            ctx.attribute("top_options", tops);

            ctx.render("orders.html");

        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            ctx.redirect("/");
        }
    }



}
