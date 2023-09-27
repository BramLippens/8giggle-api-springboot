package dev.brampie.giggleapi.service;

import dev.brampie.giggleapi.dto.UpdatedUserRequest;
import dev.brampie.giggleapi.dto.UpdatedUserResponse;
import dev.brampie.giggleapi.model.User;
import dev.brampie.giggleapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UpdatedUserResponse update(UpdatedUserRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();//TODO: Throw a better exception

        user.setBio(request.getBio());
        user.setProfilePicture(request.getProfilePicture());

        userRepository.save(user);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String createdAtFormatted = dateFormat.format(user.getCreatedAt());
        String updatedAtFormatted = dateFormat.format(user.getUpdatedAt());

        return UpdatedUserResponse.builder()
                .username(user.getUsername())
                .bio(user.getBio())
                .profilePicture(user.getProfilePicture())
                .createdAt(createdAtFormatted)
                .updatedAt(updatedAtFormatted)
                .build();
    }
}
