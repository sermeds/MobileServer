package MobileServer.controllers;

import MobileServer.models.*;
import MobileServer.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final TestService service;

    @GetMapping("/")
    public String getString() {
        return "Hello world";
    }

    @GetMapping("/test")
    public TestResponse getTest(@RequestParam(name = "testId") int testId, @AuthenticationPrincipal User currentUser) {
       return service.getTest(currentUser, testId);
    }

    @GetMapping("/tests")
    public List<Test> getAllTest() {
        return service.getAllTest();
    }

}
