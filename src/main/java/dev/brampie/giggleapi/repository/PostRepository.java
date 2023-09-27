package dev.brampie.giggleapi.repository;

import dev.brampie.giggleapi.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
    Page<Post> findAllByIsPublicTrue(Pageable pageable);
}
