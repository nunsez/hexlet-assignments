package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    private static final String SESSION_ID = "sessionId";

    public static void index(Context ctx) {
        var page = getMainPage(ctx);
        ctx.render("index.jte", model("page", page));
    }

    private static MainPage getMainPage(Context ctx) {
        try {
            var id = ctx.<Long>sessionAttribute(SESSION_ID);
            var user = UsersRepository.find(id);
            return new MainPage(user.getName());
        } catch (Exception e){
            return new MainPage(null);
        }
    }

    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");
        var user = UsersRepository.findByName(name);

        if (user != null && user.getPassword().equals(encrypt(password))) {
            ctx.sessionAttribute(SESSION_ID, user.getId());
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.consumeSessionAttribute(SESSION_ID);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
