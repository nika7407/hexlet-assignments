package exercise.dto;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MainPage {
    private Object name;
    private Context ctx;
}
