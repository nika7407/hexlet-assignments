package exercise.dto;
import lombok.Getter;
// BEGIN
@Getter
public class BasePage {
    private String flash;

    public BasePage(String flash) {
        this.flash = flash;
    }
}
// END
