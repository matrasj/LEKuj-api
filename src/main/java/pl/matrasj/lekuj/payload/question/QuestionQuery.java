package pl.matrasj.lekuj.payload.question;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.matrasj.lekuj.payload.answer.AnswerQuery;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionQuery {
    Long id;
    String content;
    Long categoryId;
    List<AnswerQuery> answers;
}
