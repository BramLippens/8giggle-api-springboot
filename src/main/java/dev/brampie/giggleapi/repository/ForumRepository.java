package dev.brampie.giggleapi.repository;

import dev.brampie.giggleapi.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, String> {
}
