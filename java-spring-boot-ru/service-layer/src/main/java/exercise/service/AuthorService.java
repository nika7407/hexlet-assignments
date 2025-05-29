package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;


    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> list = new ArrayList<>();
        authorRepository.findAll().forEach(auth ->
                list.add(mapper.map(auth)));
        return list;
    }

    public AuthorDTO getAuthor(Long id) {
        var idk = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND"));

        return mapper.map(idk);

    }

    public Author getPlainAuthor(Long id) {
        var idk = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND"));
        return idk;
    }

    public void addAuthor(AuthorCreateDTO author) {
        authorRepository.save(mapper.map(author));
    }

    public void delete(Long id){
        var idk = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOTFOUND"));
        authorRepository.deleteById(id);
    }

    public void update(AuthorUpdateDTO dto, Author author){
        mapper.update(dto,author);
        authorRepository.save(author);
    }
    // END
}
