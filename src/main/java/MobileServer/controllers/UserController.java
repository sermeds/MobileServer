package MobileServer.controllers;

import MobileServer.models.Avatar;
import MobileServer.models.AvatarElementRequest;
import MobileServer.models.User;
import MobileServer.models.enums.AvatarElementType;
import MobileServer.models.enums.Role;
import MobileServer.services.AvatarService;
import MobileServer.services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService service;
    private final AvatarService avatarService;

    @PostMapping("/avatar/create")
    public void createAvatar(@RequestBody List<AvatarElementRequest> elements, @AuthenticationPrincipal User user) {
//        List<AvatarElementRequest> list = new ArrayList<>(Arrays.asList(elements));
        avatarService.create(elements, user);
    }

    @GetMapping("/avatar/find")
    public Avatar findAvatar(@RequestParam(name = "id") int id) {
        return avatarService.find(id);
    }

    @GetMapping("/leaderboardByBalance")
    public List<User> getLeadersByBalance() {
        return service.leaderboardByBalance();
    }

    @GetMapping("/leaderboardByExp")
    public List<User> getLeadersByExp() {
        return service.leaderboardByExp();
    }

}
