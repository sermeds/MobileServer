package MobileServer.repositories;

import MobileServer.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepo extends JpaRepository<Test, Integer> {
    Test findById(int id);

//    List<Test> findAll();
}
