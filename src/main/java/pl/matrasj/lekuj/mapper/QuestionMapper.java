package pl.matrasj.lekuj.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import pl.matrasj.lekuj.entity.QuestionEntity;
import pl.matrasj.lekuj.payload.question.QuestionCommand;
import pl.matrasj.lekuj.payload.question.QuestionQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionMapper {
    AnswerMapper answerMapper;

    public QuestionQuery toQuery(QuestionEntity question) {
        return QuestionQuery.builder()
                .id(question.getId())
                .content(question.getContent())
                .categoryId(question.getCategoryId())
                .answers(answerMapper.toQuery(question.getAnswers()))
                .build();
    }

    public List<QuestionQuery> toQuery(List<QuestionEntity> questions) {
        return questions.stream()
                .map(this::toQuery)
                .collect(Collectors.toList());
    }

    public List<QuestionEntity> toEntity(List<QuestionCommand> questionCommands) {
        return questionCommands.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public QuestionEntity toEntity(QuestionCommand questionCommand) {
        return QuestionEntity.builder()
                .content(questionCommand.getContent())
                .categoryId(questionCommand.getCategoryId())
                .build();
    }
}
