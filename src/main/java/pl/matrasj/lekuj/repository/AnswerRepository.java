package pl.matrasj.lekuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.matrasj.lekuj.entity.AnswerEntity;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    void deleteAllByQuestionId(Long questionId);
}
