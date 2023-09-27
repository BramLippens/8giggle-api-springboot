package dev.brampie.giggleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String id;
    private String title;
    private String content;
    private Boolean isPublic;
    private String[] tags;
}
