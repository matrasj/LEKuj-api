package pl.matrasj.lekuj.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
public class QuestionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "content")
    String content;

    @Column(name = "content_data_file_id")
    Long contentDataFileId;

    @Column(name = "explanation_content")
    String explanationContent;

    @Column(name = "explanation_data_file_id")
    Long explanationDataFileId;

    @Column(name = "category_id")
    Long categoryId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    List<AnswerEntity> answers = new ArrayList<>();
}
