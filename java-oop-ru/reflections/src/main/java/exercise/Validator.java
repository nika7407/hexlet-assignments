package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {
    public static List<String> validate(Address adress) throws IllegalAccessException {
    List<String> invalidFields = new ArrayList<>();
        Field[] fields = adress.getClass().getDeclaredFields();

        for (Field field : fields){

            if(field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                Object value = field.get(adress);
                if (value == null) {
                    invalidFields.add(field.getName());
                   }
            }
        }
  return invalidFields;
    }
}
// END
