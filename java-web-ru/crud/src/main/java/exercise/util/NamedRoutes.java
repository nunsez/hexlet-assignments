package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }

    public static String postPath(Long id) {
        return "/posts/" + id;
    }

    public static String postRoute() {
        return "/posts/{id}";
    }
    // END
}
