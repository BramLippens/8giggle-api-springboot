package dev.brampie.giggleapi.post;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest extends CreatePostRequest {
    private String id;
}
