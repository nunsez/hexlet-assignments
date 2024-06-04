package exercise.dto.posts;

import java.util.List;
import java.util.Map;
import io.javalin.validation.ValidationError;
import exercise.model.Post;

// BEGIN
public record EditPostPage(
    Post post,
    Map<String, List<ValidationError<Object>>> errors
) {
    public EditPostPage(Post post) {
        this(post, null);
    }
}
// END
