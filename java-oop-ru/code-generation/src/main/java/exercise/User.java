package exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

// BEGIN
@Value
@Data
@Setter
@Getter
// END
class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}
