package app;

import app.config.ThymeleafConfig;
import app.controllers.CupcakeController;
import app.controllers.Controller;
import app.controllers.UserController;
import app.persistence.ConnectionPool;
import app.persistence.Database;
import app.persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import static app.controllers.Controller.getAllOrders;


public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "cupcake_shop";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args)
    {

        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
            config.staticFiles.add("/templates");
        }).start(7070);




        app.get("/", ctx ->  ctx.render("index.html"));
        app.get("/adminPageAllOrders", ctx -> Controller.getAllOrders(ctx, connectionPool));
        Controller.addRoutes(app, connectionPool);
        UserController.addRoutes(app, connectionPool);
        CupcakeController.addRoutes(app, connectionPool);


    }
}
