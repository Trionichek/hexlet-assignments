package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {
    @NotNull
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+\\d*$", message = "ID must start with TX", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Size(min = 11, max = 13, message
            = "phoneNumber must be between 10 and 13 characters")
    private String phoneNumber;

    @Size(min = 4, max = 4)
    private String clubCard;

    @Future(message = "должно содержать сегодняшнее число или дату, которая еще не наступила")
    private LocalDate cardValidUntil;
}
// END
