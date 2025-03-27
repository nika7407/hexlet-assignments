package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var company_id = ctx.pathParam("id");
            Integer needed_index = null;
            for (int i = 0; i < COMPANIES.size(); i++) {
                Map<String, String> map = COMPANIES.get(i);
                if (map.containsKey("id") && map.get("id").equals(company_id)) {
                    needed_index = i;
                    break;  // Stop once the company is found
                }
            }

            if (needed_index == null) {
                throw new NotFoundResponse("Company not found");
            } else {
                ctx.json(COMPANIES.get(needed_index));
            }
        });

        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
