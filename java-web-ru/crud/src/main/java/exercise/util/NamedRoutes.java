package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN

    public static String postsPath(){
        return "/posts";
    }


    public static String postPath(String id) {
        return "/posts/" + id;
    }

    // END
}
