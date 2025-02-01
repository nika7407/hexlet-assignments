package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{

    private String body;
    private List<Tag> children;

    public PairedTag(String tag, Map<String, String> attributes, String body, List<Tag> children) {
        super(tag, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString(){
        StringBuilder asString = new StringBuilder();
        asString.append(new SingleTag(tag, attributes));
                asString.append(body);
                children.forEach(child -> asString.append(child.toString()));
                asString.append("</" + tag + ">");
        return asString.toString();
    }

//    Tag p = new PairedTag(
//            "p",
//            Map.of("id", "abc"),
//            "Text paragraph",
//            new ArrayList<Tag>()
//    );

//p.toString(); // <p id="abc">Text paragraph</p>
//
//    Tag div = new PairedTag(
//            "div",
//            Map.of("class", "y-5"),
//            "",
//            List.of(
//                    new SingleTag("br", Map.of("id", "s")),
//                    new SingleTag("hr", Map.of("class", "a-5"))
//            )
//    )
//
//div.toString(); // <div class="y-5"><br id="s"><hr class="a-5"></div>

}
// END
