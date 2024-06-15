package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public final class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> index() {
        return postRepository.findAll().stream()
            .map(post -> {
                var comments = commentRepository.findByPostId(post.getId());
                return postToDTO(post, comments);
            })
            .toList();
    }

    @GetMapping("/{id}")
    public PostDTO show(@PathVariable Long id) {
        var post = postRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post with id %s not found".formatted(id)));
        var comments = commentRepository.findByPostId(id);
        return postToDTO(post, comments);
    }

    private CommentDTO commentToDTO(Comment comment) {
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }

    private PostDTO postToDTO(Post post, List<Comment> comments) {
        var commentsDTO = comments.stream().map(this::commentToDTO).toList();
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(commentsDTO);
        return dto;
    }

}
// END
