package dev.brampie.giggleapi.services;

import dev.brampie.giggleapi.domain.users.Role;
import dev.brampie.giggleapi.domain.users.User;
import dev.brampie.giggleapi.dto.UserRequest;
import dev.brampie.giggleapi.dto.UserResponse;
import dev.brampie.giggleapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserResponse.Login register(UserRequest.Register request) {
        User user = User.builder()
                .username(request.username)
                .email(request.email)
                .password(passwordEncoder.encode(request.password))
                .role(Role.USER)
                .enabled(true)
                .build();
        userRepository.save(user);
        return setUserInfo(user);
    }

    public UserResponse.Login authenticate(UserRequest.Login request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));
        var user = userRepository.findByUsername(request.username).orElseThrow();//TODO: Throw a better exception
        return setUserInfo(user);
    }

    private UserResponse.Login setUserInfo(User user) {
        var jwtToken = jwtService.generateToken(user);
        UserResponse.Login response = new UserResponse.Login();
        response.id = user.getId();
        response.email = user.getEmail();
        response.username = user.getUsername();
        response.accessToken = jwtToken;
        return response;
    }
}
