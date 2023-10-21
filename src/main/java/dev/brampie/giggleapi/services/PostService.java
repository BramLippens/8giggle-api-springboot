package dev.brampie.giggleapi.services;


import dev.brampie.giggleapi.domain.posts.Post;
import dev.brampie.giggleapi.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void savePostToDatabase(MultipartFile file, String title) {
        Post post = new Post();

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }
        try {
            post.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (Exception e) {
            System.out.println("not a valid file");
        }
        post.setTitle(title);
        postRepository.save(post);
    }

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow();
    }

    public void deletePostById(String id) {
        postRepository.deleteById(id);
    }

    public void updatePostById(String id, Post post) {
        Post existingPost = postRepository.findById(id).orElseThrow();
        existingPost.setTitle(post.getTitle());
        existingPost.setImage(post.getImage());
        postRepository.save(existingPost);
    }
}
