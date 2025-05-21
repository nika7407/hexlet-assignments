package exercise.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Setter
@Getter
public class CarUpdateDTO {

        private JsonNullable<String> model = JsonNullable.undefined();
        private JsonNullable<String> manufacturer = JsonNullable.undefined();
        private JsonNullable<Integer> enginePower = JsonNullable.undefined();


}
// END
