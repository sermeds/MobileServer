package MobileServer.services;

import MobileServer.models.Tip;
import MobileServer.repositories.TipRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipService {
    private final TipRepo repo;

    public Tip findById(int id) {
        return repo.findById(id);
    }

    public List<Tip> findAll() {
        return repo.findAll();
    }

    public void create(Tip tip) {
        repo.save(tip);
    }
}
