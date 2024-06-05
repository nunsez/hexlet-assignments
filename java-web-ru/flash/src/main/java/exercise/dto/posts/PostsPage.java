package exercise.dto.posts;

import java.util.ArrayList;
import java.util.List;
import exercise.model.Post;
import exercise.dto.BasePage;

// BEGIN
public class PostsPage extends BasePage {
    List<Post> posts;

    public PostsPage(List<Post> posts) {
        this.posts = posts == null ? new ArrayList<>() : posts;
    }

    public List<Post> posts() {
        return posts;
    }
}
// END
