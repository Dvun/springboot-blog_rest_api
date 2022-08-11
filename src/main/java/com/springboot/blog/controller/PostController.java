package com.springboot.blog.controller;

import com.springboot.blog.dto.post.PostDto;
import com.springboot.blog.dto.post.UpdatePostDto;
import com.springboot.blog.service.post.IPostService;
import com.springboot.blog.utils.AppConstants;
import com.springboot.blog.utils.post.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/posts")
public class PostController {
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto dto) {
        return new ResponseEntity<>(postService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_SIZE, required = false) int size,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sort", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sort) {
        return postService.getAll(page, size, sort, sortBy);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable("id") Long id) {
        return postService.getById(id);
    }

    @PutMapping("/{id}")
    public PostDto update(@PathVariable("id") Long id, @Valid @RequestBody UpdatePostDto dto) {
        return postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post deleted successfully!", HttpStatus.OK);
    }
}
