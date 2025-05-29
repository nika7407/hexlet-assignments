package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repo;

    @Autowired
    private BookMapper mapper;

    @Autowired
    private AuthorRepository autorrepo;

    public List<BookDTO> getBooks() {

        List<BookDTO> list = new ArrayList<>();
        repo.findAll().forEach(book -> list.add(mapper.map(book)));
        return list;
    }

    public BookDTO getBookDto(Long id) {
        var book = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND"));
        return mapper.map(book);
    }

    public BookDTO getBook(Long id) {
        var book = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND"));
        return mapper.map(book);
    }

    public void add(BookCreateDTO createDTO) {

        if (!autorrepo.existsById(createDTO.getAuthorId())) {
            throw new IllegalArgumentException("author is do not exists");
        }
        repo.save(mapper.map(createDTO));
    }

    public void update(BookUpdateDTO updateDTO, Long id) {
        var book = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("book do not exists"));

        if (updateDTO.getAuthorId() != null) {
            if (!autorrepo.existsById(updateDTO.getAuthorId().get())) {
                throw new IllegalArgumentException("author is do not exists");
            }
        }

        mapper.update(updateDTO, book);
        repo.save(book);
    }

    public void delete(Long id) {
        var book = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND"));

        repo.deleteById(id);
    }


    // END
}
