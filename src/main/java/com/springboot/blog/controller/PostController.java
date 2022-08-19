package com.springboot.blog.controller;

import com.springboot.blog.dto.post.CreatePostDto;
import com.springboot.blog.dto.post.PostDto;
import com.springboot.blog.dto.post.UpdatePostDto;
import com.springboot.blog.service.post.PostService;
import com.springboot.blog.utils.AppConstants;
import com.springboot.blog.dto.post.PostResponseDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/posts")
@Tag(name = "Post")
public class PostController {
    private final PostService postService;


    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<PostDto> create(@Valid @RequestBody CreatePostDto dto) {
        return postService.create(dto);
    }

    @GetMapping
    public PostResponseDto getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_SIZE, required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sort) {
        return postService.getAll(page, size, sort, sortBy);
    }

    @GetMapping(value = "/{id}")
    public PostDto getById(@PathVariable("id") Long id) {
        return postService.getById(id);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public PostDto update(@PathVariable("id") Long id, @Valid @RequestBody UpdatePostDto dto) {
        return postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post deleted successfully!", HttpStatus.OK);
    }

}
