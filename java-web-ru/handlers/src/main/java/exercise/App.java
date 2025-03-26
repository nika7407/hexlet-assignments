package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        Javalin someshit = Javalin.create()
                .get("/phones", ctx -> ctx.json(Data.getPhones()));

        someshit.get("domains", idk -> idk.json(Data.getDomains()));
        // END

        return someshit;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
