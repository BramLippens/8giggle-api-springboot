package dev.brampie.giggleapi.demo;

import dev.brampie.giggleapi.post.Post;
import dev.brampie.giggleapi.post.PostRepository;
import dev.brampie.giggleapi.tag.Tag;
import dev.brampie.giggleapi.tag.TagRepository;
import dev.brampie.giggleapi.user.User;
import dev.brampie.giggleapi.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class MockDataInitializer implements CommandLineRunner {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${app.development.mockdata.enabled}")
    private boolean mockDataEnabled;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void mockData() {
        User user = User.builder().username("brampie").password(passwordEncoder.encode("Test123")).profilePicture("https://i.pravatar.cc/300").bio("I'm a developer").build();
        userRepository.save(user);

        Tag tag = Tag.builder().name("funny").build();
        Tag tag2 = Tag.builder().name("serious").build();
        tagRepository.save(tag);
        tagRepository.save(tag2);

        Post post = Post.builder().title("My first post").content("This is my first post").author(user).build();
        post.addTag(tag);
        post.addTag(tag2);
        postRepository.save(post);

        Post post2 = Post.builder().title("My second post").content("This is my second post").author(user).build();
        post2.addTag(tag2);
        postRepository.save(post2);
    }

    @Override
    public void run(String... args) throws Exception {
        if (mockDataEnabled) mockData();
    }
}
