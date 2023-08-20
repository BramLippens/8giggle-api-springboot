package dev.brampie.giggleapi.post;

import dev.brampie.giggleapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findByTitle(String title);
    Optional<Post> findByAuthor(User author);
}
