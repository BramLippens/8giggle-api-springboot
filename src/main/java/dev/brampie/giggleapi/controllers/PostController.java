package dev.brampie.giggleapi.controllers;

import dev.brampie.giggleapi.domain.posts.Post;
import dev.brampie.giggleapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<Post>> getAllPosts(Pageable pageable) {
        System.out.println("get all posts");
        return ResponseEntity.ok(postService.getAllPosts(pageable));
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        System.out.println("save post with title: " + title);
        postService.savePostToDatabase(file, title);
        return ResponseEntity.ok().build();
    }
}
