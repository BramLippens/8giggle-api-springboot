package dev.brampie.giggleapi.services;

import dev.brampie.giggleapi.domain.users.User;
import dev.brampie.giggleapi.dto.UserRequest;
import dev.brampie.giggleapi.dto.UserResponse;
import dev.brampie.giggleapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return user.get();
    }

    public UserResponse.Update update(UserRequest.Update request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        if (request.email != null) user.setEmail(request.email);
        if (request.password != null) user.setPassword(request.password);
        if (request.username != null) user.setUsername(request.username);
        if (request.profilePicture != null) user.setProfilePicture(request.profilePicture);
        userRepository.save(user);

        UserResponse.Update response = new UserResponse.Update();
        response.username = user.getUsername();
        response.email = user.getEmail();
        response.profilePicture = user.getProfilePicture();
        return response;
    }
}
