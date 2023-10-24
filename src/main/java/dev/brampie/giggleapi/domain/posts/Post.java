package dev.brampie.giggleapi.domain.posts;

import dev.brampie.giggleapi.domain.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likes;

    @ManyToMany
    @JoinTable(
            name = "post_dislikes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> dislikes;

    public void addLike(User user) {
        if (!likes.contains(user)) {
            likes.add(user);
            user.getLikedPosts().add(this); // You might need to set up a similar relationship in your User entity.
        }
    }

    // Remove a like from the post
    public void removeLike(User user) {
        likes.remove(user);
        user.getLikedPosts().remove(this);
    }

    // Add a dislike to the post
    public void addDislike(User user) {
        if (!dislikes.contains(user)) {
            dislikes.add(user);
            user.getDislikedPosts().add(this); // Similar relationship needed in User entity.
        }
    }

    // Remove a dislike from the post
    public void removeDislike(User user) {
        dislikes.remove(user);
        user.getDislikedPosts().remove(this);
    }
}
