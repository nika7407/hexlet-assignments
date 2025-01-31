package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private final Map<String, String> map;
    private final String path;


    public FileKV(Map<String, String> Initialmap, String path) {
        this.map = new HashMap<>(Initialmap);
        this.path = path;
    }


    public void set(String key, String value) {

    }

    public void unset(String key) {

    }

    public String get(String key, String defaultValue) {
        return "";
    }

    public Map<String, String> toMap() {
        return Map.of();
    }
}
// END
