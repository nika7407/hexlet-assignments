package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage data){
        Map<String,String> map = data.toMap();
        map.forEach((key, value) -> {
            data.unset(key);
            data.set(value,key);
        });

    }
}
// END
