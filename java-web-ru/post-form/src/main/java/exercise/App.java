package exercise;

import io.javalin.Javalin;
import java.util.List;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        // BEGIN
        app.post("/users", ctx -> {
            var firstName = ctx.formParamAsClass("firstName", String.class).getOrDefault("");
            firstName = StringUtils.capitalize(firstName.strip());

            var lastName = ctx.formParamAsClass("lastName", String.class).getOrDefault("");
            lastName = StringUtils.capitalize(lastName.strip());

            var email = ctx.formParamAsClass("email", String.class).getOrDefault("");
            email = email.strip().toLowerCase();

            var password = ctx.formParamAsClass("password", String.class).getOrDefault("");
            password = Security.encrypt(password);

            var user = new User(firstName, lastName, email, password);
            UserRepository.save(user);

            ctx.redirect("/users");
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
