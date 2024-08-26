package pl.matrasj.lekuj.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.matrasj.lekuj.entity.AnswerEntity;
import pl.matrasj.lekuj.entity.QuestionEntity;
import pl.matrasj.lekuj.mapper.AnswerMapper;
import pl.matrasj.lekuj.mapper.QuestionMapper;
import pl.matrasj.lekuj.payload.question.QuestionCommand;
import pl.matrasj.lekuj.payload.question.QuestionQuery;
import pl.matrasj.lekuj.repository.AnswerRepository;
import pl.matrasj.lekuj.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionService {
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    QuestionMapper questionMapper;
    AnswerMapper answerMapper;

    @Transactional
    public QuestionQuery saveQuestion(QuestionCommand questionCommand) {
        if (questionCommand.getQuestionId() != null) {
            answerRepository.deleteAllByQuestionId(questionCommand.getQuestionId());
            questionRepository.deleteById(questionCommand.getQuestionId());
        }

        QuestionEntity createdQuestion = questionRepository.save(questionMapper.toEntity(questionCommand));
        questionCommand.getAnswers().forEach(answerCommand -> answerCommand.setQuestionId(createdQuestion.getId()));
        List<AnswerEntity> createdAnswers = answerRepository.saveAll(answerMapper.toEntity(questionCommand.getAnswers()));
        createdQuestion.setAnswers(createdAnswers);
        return questionMapper.toQuery(createdQuestion);
    }

}
