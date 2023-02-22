package com.example.backend.controllers;

import com.example.backend.services.TestService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("sort/{key}")
    public String[] sort(@PathVariable("key") int key, @RequestBody String[] strings) {
        return testService.sort(strings, key);
    }

    @GetMapping("generate")
    public String[] generate() {
        return testService.generate();
    }
}
