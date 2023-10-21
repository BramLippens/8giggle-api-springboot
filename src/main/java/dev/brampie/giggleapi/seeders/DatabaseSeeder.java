package dev.brampie.giggleapi.seeders;

import dev.brampie.giggleapi.domain.posts.Post;
import dev.brampie.giggleapi.repositories.PostRepository;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class DatabaseSeeder {
    private PostRepository postRepository;

//    @Autowired
    public DatabaseSeeder(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

//    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedPostsTable();
    }

    private void seedPostsTable() {
        List<Post> posts = new ArrayList<>();
        posts.add(Post.builder()
                .title("Post 1")
                .image("Content 1")
                .build());
    }
}
