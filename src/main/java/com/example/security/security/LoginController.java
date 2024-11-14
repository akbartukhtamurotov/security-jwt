package com.example.security.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final JwtService jwtService;

    public LoginController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // TODO: ⬇️ This type of checking is baaad, this is just for the sake of the example. Do your own implementation :)
        if ("admin".equals(request.username()) && "password".equals(request.password())) {
            String jwtToken = jwtService.generateToken(request.username());
            return new ResponseEntity<>(new LoginResponse(jwtToken), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
