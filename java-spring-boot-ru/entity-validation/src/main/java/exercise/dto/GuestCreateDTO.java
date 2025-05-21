package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+[0-9]{11,13}$")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}")
    private String clubCard;

    @Future(message = "must be a future date")
    private LocalDate cardValidUntil;
    
}
// END
