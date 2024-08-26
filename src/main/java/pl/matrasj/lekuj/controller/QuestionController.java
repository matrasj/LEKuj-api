package pl.matrasj.lekuj.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.matrasj.lekuj.mapper.QuestionMapper;
import pl.matrasj.lekuj.payload.question.QuestionCommand;
import pl.matrasj.lekuj.payload.question.QuestionQuery;
import pl.matrasj.lekuj.repository.QuestionRepository;
import pl.matrasj.lekuj.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin
public class QuestionController {
    QuestionService questionService;
    QuestionRepository questionRepository;
    QuestionMapper questionMapper;

    @PostMapping
    public ResponseEntity<QuestionQuery> saveQuestion(@RequestBody @Valid QuestionCommand questionCommand) {
        return ResponseEntity.status(CREATED).body(questionService.saveQuestion(questionCommand));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionQuery> getQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(questionMapper.toQuery(
                questionRepository.findById(questionId).orElseThrow(EntityNotFoundException::new)
        ));
    }
}
