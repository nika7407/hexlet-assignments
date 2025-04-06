package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context ctx){
        String rawTitle = ctx.formParam("name");
        String rawContent = ctx.formParam("body");
        try {
            String title = ctx.formParamAsClass("name",String.class)
                    .check(idk -> idk.length() > 2, "Название не должно быть короче двух символов").get();

            PostRepository.save(new Post(title,rawContent));
            ctx.sessionAttribute("key", "Post was successfully created!");
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var page = new BuildPostPage(rawTitle, rawContent, e.getErrors());
            ctx.status(422);
            ctx.render("posts/build.jte", model("page", page));
        }
    }

    public static void index(Context ctx){

        String flash = ctx.consumeSessionAttribute("key");
        List<Post> posts = PostRepository.getEntities();
        PostsPage page = new PostsPage(flash, posts);
        ctx.render("posts/index.jte", model("page",page));

    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
