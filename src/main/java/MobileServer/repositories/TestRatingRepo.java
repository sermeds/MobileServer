package MobileServer.repositories;

import MobileServer.models.TestRating;
import MobileServer.models.TestRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRatingRepo extends JpaRepository<TestRating, TestRatingKey> {
}
