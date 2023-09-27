package dev.brampie.giggleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedUserResponse {
    private String username;
    private String bio;
    private String profilePicture;
    private String createdAt;
    private String updatedAt;
}
