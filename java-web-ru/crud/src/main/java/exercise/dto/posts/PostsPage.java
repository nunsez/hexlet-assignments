package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

// BEGIN
public record PostsPage(List<Post> posts, int pageNumber) {
    public PostsPage(List<Post> posts) {
        this(posts, 1);
    }
}
// END


