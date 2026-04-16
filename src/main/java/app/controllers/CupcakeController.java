package app.controllers;

import app.entities.CupcakeBottom;
import app.entities.CupcakeTop;
import app.entities.OrderLines;
import app.exception.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import app.persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;


public class CupcakeController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("/orders", ctx -> loadOrderPage(ctx, connectionPool));
        app.post("/orders", ctx -> {
            getAllBottoms(ctx, connectionPool);
            getAllTops(ctx, connectionPool);
        });

        app.post("/basket", ctx -> addToBasket(ctx, connectionPool));
        app.get("/basket", ctx -> showBasket(ctx, connectionPool));
        app.post("/checkout", ctx -> checkout(ctx, connectionPool));


    }


    private static void getAllBottoms(Context ctx, ConnectionPool connectionPool) {

        List<CupcakeBottom> allBottoms = null;

        try {
            CupcakeMapper cupcakeMapper = new CupcakeMapper();
            allBottoms = cupcakeMapper.getAllCupcakeBottoms(connectionPool);
            ctx.sessionAttribute("bottom_options", allBottoms);
            ctx.sessionAttribute("bottom_options", allBottoms);
            ctx.redirect("/orders");
            ctx.render("orders.html");

        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            ctx.redirect("/");
        }

    }

    private static void getAllTops(Context ctx, ConnectionPool connectionPool) {

        List<CupcakeTop> allTops = null;

        try {
            CupcakeMapper cupcakeMapper = new CupcakeMapper();
            allTops = cupcakeMapper.getAllCupcakeTops(connectionPool);
            ctx.sessionAttribute("top_options", allTops);
            ctx.sessionAttribute("top_options", allTops);
            ctx.render("order-page.html");

        } catch (DatabaseException e) {
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

    public static void showBasket(Context ctx, ConnectionPool connectionPool) {

        List<OrderLines> cart = ctx.sessionAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        int total = 0;
        for (OrderLines line : cart) {
            total += line.getPrice();
        }

        ctx.sessionAttribute("cart", cart);
        ctx.attribute("cart", cart);
        ctx.attribute("total", total);

        ctx.render("basket.html");
        System.out.println("Cart size: " + cart.size());
    }

    public static void addToBasket(Context ctx, ConnectionPool connectionPool) {

        CupcakeMapper cupcakeMapper = new CupcakeMapper();

        int bottomId = Integer.parseInt(ctx.formParam("bottom_Id"));
        int topId = Integer.parseInt(ctx.formParam("top_Id"));

        CupcakeBottom cupcakeBottom = cupcakeMapper.getBottomCupcakeById(bottomId, connectionPool);
        CupcakeTop cupcakeTop = cupcakeMapper.getTopCupcakeById(topId, connectionPool);
        List<OrderLines> cart = ctx.sessionAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        OrderLines orderLine = new OrderLines(cupcakeBottom, cupcakeTop, 1);

        cart.add(orderLine);
        ctx.sessionAttribute("cart", cart);
        ctx.redirect("/basket");
    }

    public static void checkout(Context ctx, ConnectionPool connectionPool) {

        List<OrderLines> cart = ctx.sessionAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            ctx.redirect("/basket");
            return;
        }

        OrderMapper orderMapper = new OrderMapper();
        int orderId = orderMapper.createOrder(connectionPool);

        for (OrderLines line : cart) {
            orderMapper.addOrderLine(orderId, line, connectionPool);
        }

        ctx.sessionAttribute("cart", new ArrayList<OrderLines>());
        ctx.redirect("/orders?orderId=" + orderId);

    }
}