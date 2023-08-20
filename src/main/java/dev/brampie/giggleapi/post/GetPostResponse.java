package dev.brampie.giggleapi.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostResponse {
    private String id;
    private String title;
    private String content;
    private String author;
    private String createdAt;
    private String updatedAt;
}
