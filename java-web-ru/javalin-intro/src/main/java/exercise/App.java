package exercise;


// BEGIN
import io.javalin.Javalin;
// END

public final class App {

    public static Javalin getApp() {

        // BEGIN
        Javalin app = Javalin.create()
                .get("/welcome",ctx -> ctx.result("Welcome to Hexlet!"));
        // END
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
