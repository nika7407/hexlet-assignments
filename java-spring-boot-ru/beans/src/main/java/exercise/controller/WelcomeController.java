package exercise.controller;

// BEGIN


//Внедрите бин типа Daytime в контроллер.
//Реализуйте в контроллере обработчик, который будет обрабатывать GET-запросы на адрес

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/// welcome и выводить приветствие в зависимости от текущего времени.
//Приветствие должно иметь вид It is night now! Welcome to Spring!

@RestController
public class WelcomeController {


    @Autowired
    private Daytime daytime;

    @GetMapping("/welcome")
    @ResponseStatus(HttpStatus.OK)
    public String welcome() {
        return "It is " + daytime.getName() + " now! Welcome to Spring!";
    }

}
// END
