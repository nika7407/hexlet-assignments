package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;


    @GetMapping("")
    public List<PostDTO> getPosts() {
        List<PostDTO> returnList = new ArrayList<>();
        var posts = postRepository.findAll();
        posts.forEach(post -> {
            var add = new PostDTO();
            add.setId(post.getId());
            add.setBody(post.getBody());
            add.setTitle(post.getTitle());

            List<CommentDTO> list = new ArrayList<>();
            commentRepository.findByPostId(post.getId()).forEach(comment -> {
                list.add(new CommentDTO(comment.getId(), comment.getBody()));
            });
            add.setComments(list);
         returnList.add(add);
        });
      return returnList;
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id){

        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

            var add = new PostDTO();
            add.setId(post.getId());
            add.setBody(post.getBody());
            add.setTitle(post.getTitle());

            List<CommentDTO> list = new ArrayList<>();
            commentRepository.findByPostId(post.getId()).forEach(comment -> {
                list.add(new CommentDTO(comment.getId(), comment.getBody()));
            });
            add.setComments(list);

        return add;

    }

}
// END
