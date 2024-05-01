package MobileServer.services;

import MobileServer.models.*;
import MobileServer.models.enums.Complexity;
import MobileServer.repositories.TestRatingRepo;
import MobileServer.repositories.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRatingRepo ratingRepo;
    private final TestRepo testRepo;

    public TestResponse getTest(User user, int testId) {
        TestResponse response = new TestResponse();
        Optional<TestRating> tr =  ratingRepo.findById(new TestRatingKey(user.getId(), testId));
        if (tr.isPresent()) {
            Test test = tr.get().getTest();
            test.calculateCorrectAnswer();
            response.setTest(test);
            response.setRating(tr.get().getRating());
            return response;
        }
        Test test = testRepo.findById(testId);
        test.calculateCorrectAnswer();
        response.setTest(test);
        return response;
    }

    public List<Test> getAllTest() {
        List<Test> tests = testRepo.findAll();
        tests.forEach(Test::calculateCorrectAnswer);
        return tests;
    }
}
