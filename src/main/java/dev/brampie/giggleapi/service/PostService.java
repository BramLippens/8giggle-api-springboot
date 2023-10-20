package dev.brampie.giggleapi.service;

import dev.brampie.giggleapi.dto.PostRequest;
import dev.brampie.giggleapi.dto.PostResponse;
import dev.brampie.giggleapi.model.Post;
import dev.brampie.giggleapi.model.Tag;
import dev.brampie.giggleapi.model.User;
import dev.brampie.giggleapi.repository.PostRepository;
import dev.brampie.giggleapi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostResponse create(PostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        var post = Post.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .author((User) userDetails)
            .isPublic(request.getIsPublic() != null && request.getIsPublic())
            .build();

        Arrays.stream(request.getTags())
            .map(tagName -> {
                return tagRepository.findByName(tagName).orElseGet(() -> {
                    var newTag = Tag.builder().name(tagName).build();
                    tagRepository.save(newTag);
                    return newTag;
                });
            })
            .forEach(post::addTag);



        postRepository.save(post);

        return PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .authorName(post.getAuthor().getUsername())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .tags(post.getTags().stream().map(Tag::getName).collect(Collectors.toSet()))
            .build();
    }

    public PostResponse getById(String id) {
        var post = postRepository.findById(id).orElseThrow();//TODO: Throw a better exception

        System.out.println(post.toString());

        if (post.isPublic()) {
            return PostResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .authorName(post.getAuthor().getUsername())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .tags(post.getTags().stream().map(Tag::getName).collect(Collectors.toSet()))
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
                .authorName(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .tags(post.getTags().stream().map(Tag::getName).collect(Collectors.toSet()))
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
                .authorName(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public void delete(String id) {
        postRepository.deleteById(id);
    }
}
