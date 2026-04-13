package app.controllers;

import app.entities.Orders;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class Controller {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

    }

    public static void getAllOrders(Context ctx, ConnectionPool connectionPool){

        List<Orders> ordersList =  OrderMapper.getAllOrders();

        ctx.attribute("Orders", ordersList);


        ctx.render("/alleordre");

    }
}
