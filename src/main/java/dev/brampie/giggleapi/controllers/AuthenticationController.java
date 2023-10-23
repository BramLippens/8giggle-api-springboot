package dev.brampie.giggleapi.controllers;

import dev.brampie.giggleapi.dto.UserRequest;
import dev.brampie.giggleapi.dto.UserResponse;
import dev.brampie.giggleapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse.Login> register(@RequestBody UserRequest.Register request) {
        try{
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse.Login> login(@RequestBody UserRequest.Login request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
