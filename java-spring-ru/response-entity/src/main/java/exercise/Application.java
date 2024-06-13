package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private final List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    // Запуск приложения
    @GetMapping("/posts") // Список постов
    public ResponseEntity<List<Post>> index(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer limit
    ) {
        var result = posts.stream().skip((long) (page - 1) * limit).limit(limit).toList();
        return ResponseEntity
            .ok()
            .header("X-Total-Count", String.valueOf(posts.size()))
            .body(result);
    }

    @PostMapping("/posts") // Создание поста
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        var location = URI.create("/posts");
        return ResponseEntity.created(location).body(post);
    }

    @GetMapping("/posts/{id}") // Вывод поста
    public ResponseEntity<Post> show(@PathVariable String id) {
        var maybePost = posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        return ResponseEntity.of(maybePost);
    }

    @PutMapping("/posts/{id}") // Обновление поста
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();

        if (maybePost.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var post = maybePost.get();
        post.setId(data.getId());
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());

        return ResponseEntity.ok().body(data);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
