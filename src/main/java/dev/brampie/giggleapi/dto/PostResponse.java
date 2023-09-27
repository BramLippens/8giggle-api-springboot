package dev.brampie.giggleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private String authorName;
    private Date createdAt;
    private Date updatedAt;
    private Set<String> tags;
}
