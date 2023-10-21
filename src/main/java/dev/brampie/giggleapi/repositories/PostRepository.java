package dev.brampie.giggleapi.repositories;

import dev.brampie.giggleapi.domain.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
//    Page<Post> findAllByIsPublicTrue(Pageable pageable);
}
