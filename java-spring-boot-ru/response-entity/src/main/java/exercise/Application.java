package exercise;

import exercise.model.Post;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    @Setter
    private static List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> postList() {
        var list = posts;

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(list.size()))
                .body(list);

    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> returnPost(@PathVariable String id) {
        var post = posts.stream()
                .filter(pos -> pos.getId().equals(id))
                .findFirst();

        if (post.isPresent()) {
            return ResponseEntity.status(200)
                    .body(post.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> add(@RequestBody Post postToAdd) {
        posts.add(postToAdd);
        return ResponseEntity.status(201).build();
    }
    // PUT /posts/{id} – Обновление поста. Должен возвращаться статус 200.
    // Если пост не существует, то должен возвращаться 404

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@RequestBody Post post){
        var foundPost = posts.stream()
                .filter(currentPost -> currentPost.getId().equals(post.getId()))
                .findFirst();
        if(foundPost.isPresent()){
            var postt = foundPost.get();
            postt.setId(post.getId());
            postt.setBody(post.getBody());
            postt.setTitle(post.getTitle());
            return  ResponseEntity.status(200).build();
        }
        //else
        return  ResponseEntity.status(404).build();


    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));

    }
}
