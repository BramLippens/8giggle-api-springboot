package dev.brampie.giggleapi.services;


import dev.brampie.giggleapi.domain.posts.Post;
import dev.brampie.giggleapi.domain.users.User;
import dev.brampie.giggleapi.dto.PostResponse;
import dev.brampie.giggleapi.repositories.PostRepository;
import dev.brampie.giggleapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void savePostToDatabase(MultipartFile file, String title) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
            Post post = new Post();
            post.setAuthor(user);
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
    }

    public Page<PostResponse.Get> getAllPosts(Pageable pageable) {
        List<PostResponse.Get> postResponses = postRepository.findAll(pageable).stream()
                .map(PostService::PostToResponse)
                .toList();
        return new PageImpl<>(postResponses, pageable, postRepository.count());
    }

    public PostResponse.Get getPostById(String id) {
        PostResponse.Get postResponse = new PostResponse.Get();
        Post post = postRepository.findById(id).orElseThrow();
        return PostToResponse(post);
    }

    private static PostResponse.Get PostToResponse(Post post) {
        PostResponse.Get postResponse = new PostResponse.Get();
        postResponse.id = post.getId();
        postResponse.title = post.getTitle();
        postResponse.image = post.getImage();
        postResponse.author = post.getAuthor().getUsername();
        postResponse.likes = post.getLikes().size();
        postResponse.dislikes = post.getDislikes().size();
        return postResponse;
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

    public void vote(String id, boolean b) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
            Post post = postRepository.findById(id).orElseThrow();
            if (b) {
                post.addLike(user);
            } else {
                post.addDislike(user);
            }
            postRepository.save(post);
        }
    }
}
