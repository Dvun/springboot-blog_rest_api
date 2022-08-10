package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.UpdatePostDto;
import com.springboot.blog.service.PostService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto dto) {
        return new ResponseEntity<>(postService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "3", required = false) int size) {
        return postService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable("id") Long id) {
        return postService.getById(id);
    }

    @PutMapping("/{id}")
    public PostDto update(@PathVariable("id") Long id, @RequestBody UpdatePostDto dto) {
        return postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post deleted successfully!", HttpStatus.OK);
    }
}
