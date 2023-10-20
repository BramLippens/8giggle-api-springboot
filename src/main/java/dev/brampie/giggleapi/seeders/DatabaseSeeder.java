package dev.brampie.giggleapi.seeders;

import dev.brampie.giggleapi.model.Post;
import dev.brampie.giggleapi.model.Tag;
import dev.brampie.giggleapi.model.User;
import dev.brampie.giggleapi.repository.PostRepository;
import dev.brampie.giggleapi.repository.TagRepository;
import dev.brampie.giggleapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    @Autowired
    public DatabaseSeeder(PostRepository postRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedTables();
    }

    private void seedTables() {
        User user = User.builder()
                .username("brampie")
                .email("bram.lippens09@gmail.com")
                .password(new BCryptPasswordEncoder().encode("password"))
                .build();
        userRepository.save(user);

        // Define an array of example tag names
        String[] tagNames = {"Funny", "Programming", "Gaming", "Sports", "Music"};
        List<Tag> tags = new ArrayList<>();

        // Create a random number generator
        Random random = new Random();

        // Loop through the tag names
        for (String tagName : tagNames) {
            // Get or create the tag
            Tag tag = Tag.builder().name(tagName).build();

            tags.add(tag);
        }

        // Save the tags
        tagRepository.saveAll(tags);

        for(int i = 0; i < 30; i++){
            Post post = Post.builder()
                    .title("Post " + i)
                    .content("https://picsum.photos/1080")
                    .author(user)
                    .isPublic(true)
                    .build();

            // Get a random number of tags to add to the post
            int numberOfTags = random.nextInt(5) + 1;

            // Loop through the number of tags
            for (int j = 0; j < numberOfTags; j++) {
                // Get a random tag from the list of tags
                Tag tag = tags.get(random.nextInt(tags.size()));

                // Add the tag to the post
                post.addTag(tag);
            }

            // Save the post
            postRepository.save(post);
        }
    }
}
