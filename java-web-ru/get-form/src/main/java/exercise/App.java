package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            List<User> return_list = new ArrayList<>();

            if (term != null){
                for (int i = 0; i < USERS.size(); i++){
                    if (USERS.get(i).getFirstName().equalsIgnoreCase(term)){
                        return_list.add(USERS.get(i));
                    }
                }
            }
            else {
                return_list = USERS;
            }
            UsersPage idk = new UsersPage(return_list,term);
            ctx.render("users/index.jte", model("term",idk));
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
