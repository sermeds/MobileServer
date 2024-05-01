package MobileServer.repositories;

import MobileServer.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepo extends JpaRepository<Avatar, Integer> {
    Avatar findById(int id);
}
