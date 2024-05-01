package MobileServer.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TestRatingKey implements Serializable {

    public TestRatingKey() {
    }

    public TestRatingKey(int userId, int testId) {
        this.userId = userId;
        this.testId = testId;
    }

    @Column(name = "user_id")
    private int userId;

    @Column(name = "test_id")
    private int testId;
}
