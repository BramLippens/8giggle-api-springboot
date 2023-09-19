package dev.brampie.giggleapi.post;

import dev.brampie.giggleapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public CreatePostResponse create(PostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();



        var post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author((User) userDetails)
                .isPublic(request.getIsPublic() != null && request.getIsPublic())
                .build();

        postRepository.save(post);

        return CreatePostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt().toString())
                .updatedAt(post.getUpdatedAt().toString())
                .build();
    }

    public PostResponse getById(String id) {
        var post = postRepository.findById(id).orElseThrow();//TODO: Throw a better exception

        if (post.isPublic()) {
            return PostResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .author(post.getAuthor().getUsername())
                    .createdAt(post.getCreatedAt().toString())
                    .updatedAt(post.getUpdatedAt().toString())
                    .build();
        }
        throw new RuntimeException("Post is not public");
    }

    public Page<PostResponse> getAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAllByIsPublicTrue(pageable);

        return posts.map(post -> PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt().toString())
                .updatedAt(post.getUpdatedAt().toString())
                .build());
    }

    public PostResponse update(String id, PostRequest request) {
        var post = postRepository.findById(id).orElseThrow();//TODO: Throw a better exception

        if(request.getIsPublic() == null) post.setPublic(false);
        else post.setPublic(request.getIsPublic());

        if(request.getTitle() != null) post.setTitle(request.getTitle());

        if(request.getContent() != null) post.setContent(request.getContent());

        postRepository.save(post);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt().toString())
                .updatedAt(post.getUpdatedAt().toString())
                .build();
    }

    public void delete(String id) {
        postRepository.deleteById(id);
    }
}
