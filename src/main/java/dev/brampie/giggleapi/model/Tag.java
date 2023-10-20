package dev.brampie.giggleapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Post> posts = new ArrayList<>();
}
