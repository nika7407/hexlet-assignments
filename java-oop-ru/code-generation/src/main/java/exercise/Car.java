package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@AllArgsConstructor
@Data
@Getter
@Setter
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println("meow");
            return "";
        }
    }

    public static Car deserialize(String carAsJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(carAsJson, Car.class);
        } catch (JsonProcessingException e) {
            System.out.println("meow");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
