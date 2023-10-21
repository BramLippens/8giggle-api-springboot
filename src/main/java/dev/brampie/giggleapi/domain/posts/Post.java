package dev.brampie.giggleapi.domain.posts;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="posts")
public class Post {
    @Id
    @GenericGenerator(name = "post_id", strategy = "dev.brampie.giggleapi.domain.posts.PostIdGenerator")
    @GeneratedValue(generator = "post_id")
    @Column(name="post_id", unique = true, nullable = false, updatable = false)
    private String id;

    @Column(name="post_title", nullable = false)
    private String title;

    @Lob
    @Column(name="post_image", nullable = false)
    private String image;

}
