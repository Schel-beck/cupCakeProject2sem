package app.controllers;

import app.entities.Users;
import app.exception.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

    }

    public static void registrerBruger(Context ctx, ConnectionPool connectionPool) {
        String name = ctx.formParam("name");
        String password = ctx.formParam("password");
        String email = ctx.formParam("email");

        String error = validateUser(name, email, password);
        if (!error.isEmpty())
        {
            ctx.attribute("msg", error);
            ctx.render("registrerbruger.html");
        } else{

            try {
                UserMapper.createUser(name, email, password, connectionPool);
                ctx.render("index.html");
            } catch (DatabaseException e) {
                ctx.attribute("msg", e.getMessage());
                ctx.render("registrerbruger.html");
            }
        }
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        try {
            Users user = UserMapper.login(email, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.redirect("/dashboard");

        } catch (DatabaseException e) {
            ctx.attribute("msg", e.getMessage());
            ctx.render("login.html");
        }
    }

    public static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }


    public static String validateUser(String name, String password, String email) {
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


}
