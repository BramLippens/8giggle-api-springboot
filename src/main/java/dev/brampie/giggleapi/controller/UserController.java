package dev.brampie.giggleapi.controller;

import dev.brampie.giggleapi.dto.UpdatedUserRequest;
import dev.brampie.giggleapi.dto.UpdatedUserResponse;
import dev.brampie.giggleapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<UpdatedUserResponse> update(@RequestBody UpdatedUserRequest request) {
        return ResponseEntity.ok(userService.update(request));
    }
}
