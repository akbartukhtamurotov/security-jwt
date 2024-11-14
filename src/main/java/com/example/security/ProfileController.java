package com.example.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    // TODO: Sample protected endpoint with token
    @GetMapping("/me")
    public String me() {
        return "me";
    }
}
