package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
//    Обработчик POST /users для регистрации пользователя.
//    Он должен принимать данные формы и устанавливать специальный токен в куке.
//    Для генерации токена используйте метод Security.generateToken().
//    Пользователь должен сохраняться в репозитории. Затем должен происходить
//    редирект на /users/{id}, где {id} — это id зарегистрированного пользователя

    public static void post(Context ctx){
       String token = Security.generateToken();
       ctx.cookie("token",token);

       var firstname = ctx.formParam("firstName");
       var lastName = ctx.formParam("lastName");
       var email = ctx.formParam("email");
       var password = Security.encrypt(ctx.formParam("password"));

       var user = new User(firstname,lastName,email,password,token);
           UserRepository.save(user);
       ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

//    Обработчик GET /users/{id}. Он должен отображать данные пользователя на странице,
//    но только в том случае, если токен пользователя в куке совпадает с
//    токеном пользователя, id которого указан в адресе.
//    Для поиска пользователя используйте метод find() из класса репозитория.
//    Затем обработчик должен отрисовывать шаблон users/show.jte с данными пользователя.
//    Если пользователь не совпадает по токену,
//    должен происходить редирект на /users/build


    public static void show(Context ctx) {
        Long id = Long.valueOf(ctx.pathParam("id"));
        var page = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));

        String token = ctx.cookie("token");
         UserPage idk = new UserPage(page);
        if (token != null && token.equals(page.getToken())) {
            ctx.render("users/show.jte", model("page", idk));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
