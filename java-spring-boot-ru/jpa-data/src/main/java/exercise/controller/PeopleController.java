package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    // BEGIN
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Person>  getPeople(){
         return personRepository.findAll();
    }

    // POST /people – создание новой персоны.
    // При успешном действии должен вернуться ответ со статусом 201 Created

    @PostMapping("")
    public ResponseEntity<Person> addPeople(@RequestBody Person personToAdd){
        personRepository.save(personToAdd);
        return ResponseEntity.status(201)
                .body(personToAdd);
    }

   //  DELETE /people/{id} – удаление персоны.
   // При успешном действии должен вернуться ответ со статусом 204 No Content

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
    }

    // END
}
