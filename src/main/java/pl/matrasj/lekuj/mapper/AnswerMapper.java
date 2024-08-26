package pl.matrasj.lekuj.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import pl.matrasj.lekuj.entity.AnswerEntity;
import pl.matrasj.lekuj.payload.answer.AnswerCommand;
import pl.matrasj.lekuj.payload.answer.AnswerQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerMapper {
    public AnswerQuery toQuery(AnswerEntity answer) {
        return AnswerQuery.builder()
                .content(answer.getContent())
                .correct(answer.isCorrect())
                .build();
    }

    public List<AnswerQuery> toQuery(List<AnswerEntity> answers) {
        return answers
                .stream()
                .map(this::toQuery)
                .collect(Collectors.toList());
    }

    public AnswerEntity toEntity(AnswerCommand answerCommand) {
        return AnswerEntity.builder()
                .questionId(answerCommand.getQuestionId())
                .content(answerCommand.getContent())
                .correct(answerCommand.isCorrect())
                .build();
    }

    public List<AnswerEntity> toEntity(List<AnswerCommand> answerCommands) {
        return answerCommands
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
