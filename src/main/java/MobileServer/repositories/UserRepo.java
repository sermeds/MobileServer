package MobileServer.repositories;

import MobileServer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    List<User> findDistinctTop100ByOrderByBalanceDesc();

    List<User> findDistinctTop100ByOrderByExpDesc();

}
