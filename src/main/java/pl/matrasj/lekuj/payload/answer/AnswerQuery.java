package pl.matrasj.lekuj.payload.answer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerQuery {
    String content;
    boolean correct;
}
