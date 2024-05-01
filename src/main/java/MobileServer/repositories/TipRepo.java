package MobileServer.repositories;

import MobileServer.models.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipRepo extends JpaRepository<Tip, Integer> {
    Tip findById(int id);
}
