package MobileServer.services;

import MobileServer.models.*;
import MobileServer.models.enums.AvatarElementType;
import MobileServer.repositories.AvatarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepo repo;

    public void create(List<AvatarElementRequest> elements, User user) {
        Avatar avatar = new Avatar();
        avatar.setUser(user);
        avatar.setId(user.getId());
        List<AvatarElement> avatarElements = transform2AvatarElements(elements, user);
        avatar.setElements(avatarElements);
        repo.save(avatar);
    }

    public Avatar find(int id) {
        return repo.findById(id);
    }

    public List<AvatarElement> transform2AvatarElements(List<AvatarElementRequest> elements, User user) {
        List<AvatarElement> avatars = new ArrayList<>();
        for (AvatarElementRequest a: elements) {
            AvatarElement ae = new AvatarElement();
            ae.setId(new AvatarElementKey(user.getId(), a.getType()));
            ae.setColor(a.getColor());
            ae.setNumber(a.getNumber());
            avatars.add(ae);
        }
        return avatars;
    }
}
