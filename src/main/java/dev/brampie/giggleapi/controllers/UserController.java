package dev.brampie.giggleapi.controllers;

import dev.brampie.giggleapi.dto.UserRequest;
import dev.brampie.giggleapi.dto.UserResponse;
import dev.brampie.giggleapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<UserResponse.Update> update(@RequestBody UserRequest.Update request) {
        return ResponseEntity.ok(userService.update(request));
    }
}
