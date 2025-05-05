package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.awt.desktop.SystemSleepEvent;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()){
            if (method.isAnnotationPresent(Inspect.class)){

                String ret = "Method name: " + method.getName() + "\n"
                + "Return type: " + method.getReturnType().getName() + "\n";

                System.out.println(ret);

            }
        }


        // END
    }
}
