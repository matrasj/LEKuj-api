package pl.matrasj.lekuj.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.matrasj.lekuj.payload.question.QuestionCommand;
import pl.matrasj.lekuj.payload.question.QuestionQuery;
import pl.matrasj.lekuj.service.QuestionService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin
public class QuestionController {
    QuestionService questionService;
    @PostMapping
    public ResponseEntity<QuestionQuery> saveQuestion(@RequestBody @Valid QuestionCommand questionCommand) {
        return ResponseEntity.status(CREATED).body(questionService.saveQuestion(questionCommand));
    }
}
