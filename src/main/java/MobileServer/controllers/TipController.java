package MobileServer.controllers;

import MobileServer.models.Tip;
import MobileServer.services.TipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class TipController {
    private final TipService service;

    @GetMapping("/tip/findById")
    public Tip getTipById(@RequestParam(name = "tipId") int tipId) {
        return service.findById(tipId);
    }

    @GetMapping("/tips")
    public List<Tip> getAllTips() {
        return service.findAll();
    }

    @PostMapping("/tip/create")
    public void create(@RequestBody Tip tip) {
        service.create(tip);
    }
}
