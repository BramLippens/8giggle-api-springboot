package dev.brampie.giggleapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
