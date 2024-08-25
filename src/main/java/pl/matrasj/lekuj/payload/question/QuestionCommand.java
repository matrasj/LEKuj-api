package pl.matrasj.lekuj.payload.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.matrasj.lekuj.payload.answer.AnswerCommand;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionCommand {
    Long questionId;
    @NotBlank String content;
    @NotNull Long categoryId;
    @NotEmpty List<AnswerCommand> answers;
}
