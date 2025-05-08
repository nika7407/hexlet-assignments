package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;
import lombok.Getter;
import lombok.Setter;

// BEGIN

@Getter
@Setter
@Entity
@Table( name = "person")
public class Person{

    // —оздайте модель Person со свойствами, котора€ будет представл€ть человека в нашем
    // приложении. ” человека есть уникальный идентификатор, им€ firstName и фамили€ lastName.
    // »дентификатор должен генерироватьс€ автоматически.
@Id
@GeneratedValue(strategy = IDENTITY)
private int id;
@Column
private String firstName;
@Column
private String lastName;

}

// END
