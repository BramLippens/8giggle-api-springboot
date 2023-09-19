package dev.brampie.giggleapi.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<CreatePostResponse> create(@RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.create(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(postService.getAll(pageable));
    }

    @PutMapping
    public ResponseEntity<PostResponse> update(@RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }
}
