package exercise.dto.articles;

import java.util.Map;
import java.util.List;
import io.javalin.validation.ValidationError;

// BEGIN
public record BuildArticlePage(
    String title,
    String content,
    Map<String, List<ValidationError<Object>>> errors
) {
    public BuildArticlePage() {
        this("", "", null);
    }
}
// END
