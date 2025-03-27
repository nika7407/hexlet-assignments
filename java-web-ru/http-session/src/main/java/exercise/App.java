package exercise;

import io.javalin.Javalin;
import io.javalin.validation.Validator;

import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", idk -> {
            Integer page = idk.queryParamAsClass("page",Integer.class).getOrDefault(1);
            Integer per = idk.queryParamAsClass("per", Integer.class).getOrDefault(5);

            List<Map<String, String>> return_list = Data.getUsers();

            idk.json(return_list.subList(page*per-per,page*per));
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
