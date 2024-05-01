package MobileServer.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "test_ratings")
@EqualsAndHashCode(of = {"id"})
public class TestRating {
    @EmbeddedId
    @Column(name = "id")
    private TestRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "rating")
    private int rating;
}
