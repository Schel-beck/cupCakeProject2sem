package app.controllers;

import app.entities.Users;
import app.exception.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.eclipse.jetty.server.Authentication;

import java.util.List;


public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/login", ctx -> ctx.render("index.html"));
        app.post("/login", ctx -> login(ctx, connectionPool));


        app.post("/createAccount", ctx -> createAccount(ctx, connectionPool));
        app.get("/createAccount", ctx -> ctx.render("createAccount.html"));

        app.get("/aboutUs", ctx -> ctx.render("aboutUs"));
        app.get("/contactUs", ctx -> ctx.render("contactUs"));

        app.get("/adminUpdateBalance", ctx -> showUpdateBalancePage(ctx, connectionPool));
        app.post("/updateBalance", ctx -> updateBalance(ctx, connectionPool));
    }


    public static void createAccount(Context ctx, ConnectionPool connectionPool) {
        String name = ctx.formParam("name");
        String password = ctx.formParam("password");
        String email = ctx.formParam("email");

        String error = validateUser(name, email, password);
        if (!error.isEmpty())
        {
            ctx.attribute("msg", error);
            ctx.render("createAccount.html");
        } else{

            try {
                UserMapper.createUser(name, email, password, connectionPool);
                ctx.render("index.html");
            } catch (DatabaseException e) {
                ctx.attribute("msg", e.getMessage());
                ctx.render("createAccount.html");
            }
        }
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        try {
            Users user = UserMapper.login(email, password, connectionPool);

            if (user == null) {
                ctx.attribute("msg", "Forkert email eller password");
                ctx.render("index.html");
                return;
            }

            ctx.sessionAttribute("currentUser", user);

            if (user.isAdmin()) {
                ctx.redirect("/adminPageAllOrders");
            } else {
                user = ctx.sessionAttribute("currentUser");
                ctx.attribute("user", user);
                ctx.redirect("/orders");
            }

        } catch (DatabaseException e) {
            ctx.attribute("msg", e.getMessage());
            ctx.render("index.html");
        }
    }

    public static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }


    public static String validateUser(String name, String email, String password) {
        if (name.isEmpty()) {
            return "navn skal udfyldes";
        }
        else if (email.isEmpty()){
            return "email skal udfyldes";
        }
        else if (!email.contains("@")){
            return "email mangler @";
        }
        else if (password.isEmpty()) {
            return "Password skal udfyldes";
        }
        else if (password.length()<8){
            return "password skal være mindst 8 tegn";
        }
        else if (!password.matches(".*\\d.*")){
            return "password skal have mindst 1 tal";
        }
        else if (!password.matches(".*[^a-zA-Z0-9].*")){
            return "password skal have mindst 1 special tegn";
        }

        else return "";
    }
    public static void showUpdateBalancePage(Context ctx, ConnectionPool connectionPool) throws DatabaseException {

        List<Users> users = UserMapper.getAllUsers(connectionPool);

        ctx.attribute("users", users);

        ctx.render("adminUpdateBalance.html");
    }
    public static void updateBalance(Context ctx, ConnectionPool connectionPool) throws DatabaseException {

        int userId = Integer.parseInt(ctx.formParam("userId"));
        int amount = Integer.parseInt(ctx.formParam("amount"));

        UserMapper.updateBalance(userId, amount, connectionPool);

        ctx.redirect("/adminUpdateBalance");
    }


}
