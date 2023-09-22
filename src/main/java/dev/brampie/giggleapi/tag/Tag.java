package dev.brampie.giggleapi.tag;

import dev.brampie.giggleapi.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@Table(name="_tags")
public class Tag {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags",cascade = CascadeType.MERGE)
    @Builder.Default
    private Set<Post> posts = new HashSet<>();
}
