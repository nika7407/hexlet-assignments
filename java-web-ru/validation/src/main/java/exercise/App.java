package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/articles/build", ctx -> {
            String title = "empty";
            String content = "empty";
            var idk = new BuildArticlePage (title,content,null);
           ctx.render("articles/build.jte", model("page", idk));
        });

        app.post("/articles", ctx -> {
            String rawTitle = ctx.formParam("title");
            String rawContent = ctx.formParam("content");
            try {
                String title = ctx.formParamAsClass("title",String.class)
                        .check(idk -> idk.length() > 2, "Название не должно быть короче двух символов")
                        .check(idk -> !ArticleRepository.existsByTitle(idk),"Статья с таким названием уже существует").get();

                String content = ctx.formParamAsClass("content",String.class)
                        .check(idk -> idk.length() > 10,"Статья должна быть не короче 10 символов").get();
                ArticleRepository.save(new Article(title,content));
                ctx.redirect("/articles");
            } catch (ValidationException e) {

                BuildArticlePage page = new BuildArticlePage(
                        rawTitle != null ? rawTitle : "",
                        rawContent != null ? rawContent : "",
                        e.getErrors()
                );
                ctx.status(422);
                ctx.render("articles/build.jte", model("page", page));
            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
