package pl.matrasj.lekuj.payload.answer;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerCommand {
    @NotBlank String content;
    boolean correct;
}
