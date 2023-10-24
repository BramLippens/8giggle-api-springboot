package dev.brampie.giggleapi.controllers;

import dev.brampie.giggleapi.domain.posts.Post;
import dev.brampie.giggleapi.dto.PostResponse;
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
    public ResponseEntity<Page<PostResponse.Get>> getAllPosts(Pageable pageable) {
        return ResponseEntity.ok(postService.getAllPosts(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse.Get> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Post> savePost(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        postService.savePostToDatabase(file, title);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/upvote")
    public void upvote(@PathVariable String id){
        System.out.println("upvote");
        postService.vote(id, true);
    }

    @PutMapping("/{id}/downvote")
    public void downvote(@PathVariable String id){
        postService.vote(id, false);
    }
}
