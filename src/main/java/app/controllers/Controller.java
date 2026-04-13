package app.controllers;

import app.entities.Orders;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Controller {

    public static void getAllOrders(Context ctx, ConnectionPool connectionPool){

        List<Orders> ordersList =  OrderMapper.getAllOrders(connectionPool);

        ctx.attribute("orders", ordersList);


        ctx.render("adminPageAllOrders");


    }
}
