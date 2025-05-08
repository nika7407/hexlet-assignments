package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

// BEGIN

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person{

    // Создайте модель Person со свойствами, которая будет представлять человека в нашем
    // приложении. У человека есть уникальный идентификатор, имя firstName и фамилия lastName.
    // Идентификатор должен генерироваться автоматически.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String firstName;
private String lastName;

}

// END
