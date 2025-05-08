package exercise.controller.users;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN


@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PostsController{

    List<Post> posts = Data.getPosts();

    // GET /api/users/{id}/posts — список всех постов, написанных
    // пользователем с таким же userId, как id в маршруте

    @GetMapping("/users/{id}/posts")
    public List<Post> getPosts(@PathVariable int id){
        var list = posts.stream()
                .filter(current -> current.getUserId()==id)
                .toList();

        return list;
    }

    // POST /api/users/{id}/posts – создание нового поста, привязанного к пользователю по id.
    // Код должен возвращать статус 201, тело запроса должно содержать slug, title и body.
    // Обратите внимание, что userId берется из маршрута

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> addPost(@PathVariable int id, @RequestParam Post postToAdd){

        postToAdd.setUserId(id);
        posts.add(postToAdd);
        return  ResponseEntity.status(201)
                .body(postToAdd);

    }

}


// END
