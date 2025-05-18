package exercise.controller;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;
import exercise.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO input) {
        var returnDTO = new ContactDTO();

        var firstname = input.getFirstName();
        var lastname = input.getLastName();
        var phone = input.getPhone();

        var contact = new Contact();
        contact.setFirstName(firstname);
        contact.setLastName(lastname);
        contact.setPhone(phone);

        var saveed = contactRepository.save(contact);

        returnDTO.setFirstName(saveed.getFirstName());
        returnDTO.setLastName(saveed.getLastName());
        returnDTO.setPhone(saveed.getPhone());
        returnDTO.setId(saveed.getId());
        returnDTO.setUpdatedAt(saveed.getUpdatedAt());
        returnDTO.setCreatedAt(saveed.getUpdatedAt());


        return returnDTO;
    }
    // END
}
