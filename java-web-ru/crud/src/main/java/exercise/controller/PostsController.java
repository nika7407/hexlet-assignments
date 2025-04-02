package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

 import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Objects;

public class PostsController {

    // BEGIN
    public static void show(Context ctx){
    var id = ctx.pathParamAsClass("id",long.class).get();
        Post post = PostRepository.getEntities().stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var idk = new PostPage(post);
        ctx.render("posts/show.jte",model("page", idk));
    }

    public static void index(Context ctx) {
        var page = ctx.queryParamAsClass("page", int.class).getOrDefault(1);
        var posts = PostRepository.findAll(page, 5);
        var pages = new PostsPage(posts, page);
        ctx.render("posts/index.jte", model("pages", pages));
    }

    // END
}
