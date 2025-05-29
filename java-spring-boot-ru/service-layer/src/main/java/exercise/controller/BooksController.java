package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN

    @GetMapping("")
    public  List<BookDTO> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody BookCreateDTO bookCreateDTO){

       try {
           bookService.add(bookCreateDTO);
       } catch (IllegalArgumentException e) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
       }
    }

    @PutMapping("/{id}")
    public void redact(@PathVariable Long id,@RequestBody BookUpdateDTO updateDTO){
        var book = bookService.getBook(id);
        bookService.update(updateDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }
    
    // END
}
