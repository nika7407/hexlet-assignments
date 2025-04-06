package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;

// BEGIN
@Getter
public class PostsPage extends BasePage{
    private List<Post> list;

    public PostsPage(String flash, List<Post> list) {
        super(flash);  // Pass "flash" to BasePage's constructor
        this.list = list;
    }
}

// END
