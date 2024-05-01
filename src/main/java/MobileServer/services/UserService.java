package MobileServer.services;

import MobileServer.models.User;
import MobileServer.models.enums.Role;
import MobileServer.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepo.save(user);
        return true;
    }

    public boolean findUser(String login) {
        return userRepo.findByLogin(login) != null;
    }

    public boolean findSA() {
        return userRepo.findByLogin("sa") == null;
    }


    public List<User> showUsers() {
        return userRepo.findAll();
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public List<User> leaderboardByBalance() {
        return userRepo.findDistinctTop100ByOrderByBalanceDesc();
    }

    public List<User> leaderboardByExp() {
        return userRepo.findDistinctTop100ByOrderByExpDesc();
    }


    public void updateBalance(User user, int amount) {
        user.setBalance(user.getBalance() + amount);
        userRepo.save(user);
    }

    public void addRole(User user, Role role) {
        user.getRoles().add(role);
        userRepo.save(user);
    }

}
