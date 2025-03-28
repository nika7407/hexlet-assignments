package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", ctx -> {

            long id = ctx.pathParamAsClass("id", Long.class).get();
            Long specific_user = null;

            for (int i = 0; i < USERS.size(); i++){
               var user = USERS.get(i);
               if (user.getId() == id) {
               specific_user = Long.valueOf(i);
               break;
               }
           }

            UserPage idk =  new UserPage(USERS.get(Math.toIntExact(specific_user)));

            if (specific_user == null){
                throw new NotFoundResponse("User not found");
            } else {
                ctx.render("users/show.jte", model("user", idk));
            }
        });

        app.get("/users", ctx -> {

            UsersPage idk = new UsersPage(USERS);

            ctx.render("users/index.jte", model("users", idk));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
