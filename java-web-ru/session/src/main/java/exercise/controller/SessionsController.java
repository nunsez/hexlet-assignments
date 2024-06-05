package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    private static final String SESSION_ID = "sessionId";

    public static void build(Context ctx) {
        var page = new LoginPage("", null);
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();
        var encryptedPassword = encrypt(password);

        var user = UsersRepository.findByName(name);

        if (user != null && user.getPassword().equals(encryptedPassword)) {
            ctx.sessionAttribute(SESSION_ID, user.getId());
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }


    }

    public static void delete(Context ctx) {
        ctx.consumeSessionAttribute(SESSION_ID);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void index(Context ctx) {
        var sessionId = ctx.<String>sessionAttribute(SESSION_ID);
        MainPage page;

        if (sessionId != null) {
            var id = Long.valueOf(sessionId);
            var user = UsersRepository.find(id);

            if (user != null) {
                page = new MainPage(user.getName());
            } else {
                page = new MainPage(null);
            }
        } else {
            page = new MainPage(null);
        }

        ctx.render("index.jte", model("page", page));
    }
    // END
}
