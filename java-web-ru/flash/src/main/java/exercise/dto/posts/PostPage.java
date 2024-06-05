package exercise.dto.posts;

import exercise.dto.BasePage;
import exercise.model.Post;

public class PostPage extends BasePage {
    private final Post post;

    public PostPage(Post post) {
        this.post = post;
    }

    public Post post() {
        return post;
    }
}
