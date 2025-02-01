package exercise;

import java.util.stream.Collectors;
import java.util.Map;
import java.lang.StringBuilder;



// BEGIN
public abstract class Tag {

    protected String tag;
    protected Map<String,String> attributes;

    public Tag(String tag, Map<String,String> attributes) {
        this.tag = tag;
        this.attributes= attributes;
    }

    @Override
    public String toString() {
    StringBuilder asString = new StringBuilder();
    asString.append("<"+ tag);
    attributes.forEach((key, value) -> {
        asString.append(" " + key+"=\"" + value + "\"");
    });
    asString.append(">");
    return asString.toString();
    }
}
// END
// Tag img = new SingleTag("img", Map.of("class", "v-10", "id", "wop"));
// <img class="v-10" id="wop">

