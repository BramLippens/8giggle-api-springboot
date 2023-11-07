package dev.brampie.giggleapi.controllers;

import dev.brampie.giggleapi.domain.posts.Post;
import dev.brampie.giggleapi.dto.PostResponse;
import dev.brampie.giggleapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

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
    public ResponseEntity<Post> savePost(@RequestParam("file") MultipartFile file, @RequestParam("title") String title, Principal principal) {
        postService.savePostToDatabase(file, title, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/upvote")
    public void upvote(@PathVariable String id, Principal principal){
        postService.upvote(id,principal.getName());
    }
    @DeleteMapping("/{id}/upvote")
    public void undoUpvote(@PathVariable String id, Principal principal){
        postService.undoUpvote(id, principal.getName());
        postService.undoUpvote(id, principal.getName());
    }
}
