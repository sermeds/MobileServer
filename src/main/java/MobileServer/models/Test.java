package MobileServer.models;

import MobileServer.models.enums.Complexity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tests")
@EqualsAndHashCode(of = {"id"})
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private List<Question> questions = new ArrayList<>();

    @Column(name = "complexity")
    private Complexity complexity;

    public void calculateCorrectAnswer() {
        questions.forEach(Question::calculateCorrectAnswer);
    }

}
