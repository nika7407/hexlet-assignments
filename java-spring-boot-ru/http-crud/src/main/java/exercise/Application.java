package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts") // Список страниц
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().limit(limit).toList();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post){
        posts.add(post);
        return post;
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> find(@PathVariable String id){
        return posts.stream()
                 .filter(post -> post.getId().equals(id))
                 .findFirst();
    }

    @PutMapping("/posts/{id}")
    public Optional<Post> update(@PathVariable String id, @RequestBody Post post){
        var foundPost = posts.stream()
                .filter(currentPost -> currentPost.getId().equals(id))
                .findFirst();
        if(foundPost.isPresent()){
            var postt = foundPost.get();
            postt.setId(post.getId());
            postt.setBody(post.getBody());
            postt.setTitle(post.getTitle());
        }
        return Optional.ofNullable(post);

    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id){
        var foundPost = posts.stream()
                .filter(currentPost -> currentPost.getId().equals(id))
                .findFirst();

        posts.remove(foundPost.get());
    }
    // END
}
