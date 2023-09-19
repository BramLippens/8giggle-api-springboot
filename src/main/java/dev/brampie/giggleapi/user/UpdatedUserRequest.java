package dev.brampie.giggleapi.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedUserRequest {
    private String username;
    private String email;
    private String bio;
    private String profilePicture;
}
