package MobileServer.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "questions")
@EqualsAndHashCode(of = {"id"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

    @Transient
    private int correctAnswer;

    public void calculateCorrectAnswer() {
        for (Answer a : answers) {
            if (a.isCorrect()) {
                this.correctAnswer = answers.indexOf(a);
                break;
            }
        }
    }
}
