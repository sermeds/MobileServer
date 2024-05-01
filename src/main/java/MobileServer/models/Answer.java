package MobileServer.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Data
@Table(name = "answers")
@EqualsAndHashCode(of = {"id"})
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private String value;

    @Column(name = "correct")
    private boolean correct;



}
