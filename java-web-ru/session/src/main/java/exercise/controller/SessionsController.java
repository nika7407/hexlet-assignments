package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.Generator;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void get(Context ctx) {
        //  GET /sessions/build — форма логина,
        //  в которой пользователь вводит имя и пароль
        var page = new LoginPage("",null);
        ctx.render("build.jte", model("page",page));
    }

    public static void session(Context ctx) {
        var name = ctx.formParam("name");
        var password = Security.encrypt(ctx.formParam("password"));
        var user = UsersRepository.findByName(name);

        if (user.isPresent() && password.equals(user.get().getPassword())) {
            ctx.cookie("currentUser", name); // Сохраняем имя вместо "true" // Сохраняем имя
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        var page = new LoginPage(name, "Wrong username or password");
        ctx.render("build.jte", model("page", page));
    }

    public static void deletesession(Context ctx){
        ctx.removeCookie("currentUser");
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void getMain(Context ctx){
        var page = new MainPage(ctx.cookie("currentUser"),ctx);
        ctx.render("index.jte",model("page",page));
    }

}
