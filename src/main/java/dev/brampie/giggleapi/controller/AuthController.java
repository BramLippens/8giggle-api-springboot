package dev.brampie.giggleapi.controller;

import dev.brampie.giggleapi.dto.AuthRequest;
import dev.brampie.giggleapi.dto.AuthResponse;
import dev.brampie.giggleapi.service.AuthService;
import dev.brampie.giggleapi.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authenticationService;

     @PostMapping("/register")
     public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
         try{
             return ResponseEntity.ok(authenticationService.register(request));
         } catch (Exception e) {
             return ResponseEntity.badRequest().build();
         }
     }

     @PostMapping("/authenticate")
     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
         return ResponseEntity.ok(authenticationService.authenticate(request));
     }
}
