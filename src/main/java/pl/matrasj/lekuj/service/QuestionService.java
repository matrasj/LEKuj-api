package pl.matrasj.lekuj.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.matrasj.lekuj.entity.AnswerEntity;
import pl.matrasj.lekuj.entity.QuestionEntity;
import pl.matrasj.lekuj.payload.question.QuestionCommand;
import pl.matrasj.lekuj.payload.question.QuestionQuery;
import pl.matrasj.lekuj.repository.AnswerRepository;
import pl.matrasj.lekuj.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionService {
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;

    @Transactional
    public QuestionQuery saveQuestion(QuestionCommand questionCommand) {
        if (questionCommand.getQuestionId() != null) {
            answerRepository.deleteAllByQuestionId(questionCommand.getQuestionId());

            QuestionEntity existingQuestion = questionRepository.findById(questionCommand.getQuestionId()).orElseThrow(EntityNotFoundException::new);
            existingQuestion.setContent(questionCommand.getContent());
            List<AnswerEntity> answerEntities = answerRepository.saveAll(questionCommand.getAnswers().stream().map(answerCommand -> AnswerEntity.builder()
                    .questionId(existingQuestion.getId())
                    .content(answerCommand.getContent())
                    .correct(answerCommand.isCorrect())
                    .build()).collect(Collectors.toList()));

            return QuestionQuery.builder()
                    .content(existingQuestion.getContent())
                    .build();
        }

        QuestionEntity createdQuestion = questionRepository.save(
                QuestionEntity.builder()
                        .content(questionCommand.getContent())
                        .categoryId(questionCommand.getCategoryId())
                        .build()
        );

        List<AnswerEntity> createdAnswers = answerRepository.saveAll(questionCommand.getAnswers().stream().map(answerCommand -> AnswerEntity.builder()
                .questionId(createdQuestion.getId())
                .content(answerCommand.getContent())
                .correct(answerCommand.isCorrect())
                .build()).collect(Collectors.toList()));

        return QuestionQuery.builder()
                .content(createdQuestion.getContent())
                .build();
    }
}
