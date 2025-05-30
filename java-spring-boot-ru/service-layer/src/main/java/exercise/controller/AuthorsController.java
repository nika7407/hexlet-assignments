package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping("")
    public List<AuthorDTO> getList(){
       return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id){
        return authorService.getAuthor(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AuthorCreateDTO createDTO){
        authorService.addAuthor(createDTO);
    }

    @PutMapping("/{id}")
    public void redact(@PathVariable Long id, @RequestBody AuthorUpdateDTO update){
         var author = authorService.getPlainAuthor(id);
        authorService.update(update,author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }



    // END
}
