package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;

public class BuildPostPage {
    private final String name;
    private final String body;
    private final Map<String, List<ValidationError<Object>>> errors;

    public BuildPostPage(String name, String body, Map<String, List<ValidationError<Object>>> errors) {
        this.name = name;
        this.body = body;
        this.errors = errors;
    }

    public BuildPostPage(String name, String body) {
        this(name, body, null);
    }

    public BuildPostPage() {
        this(null, null, null);
    }

    public String name() {
        return name;
    }

    public String body() {
        return body;
    }

    public Map<String, List<ValidationError<Object>>> errors() {
        return errors;
    }
}
