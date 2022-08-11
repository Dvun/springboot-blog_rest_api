package com.springboot.blog.controller;


import com.springboot.blog.dto.comment.CommentDto;
import com.springboot.blog.dto.comment.UpdateCommentDto;
import com.springboot.blog.service.comment.ICommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable(value = "postId") Long postId, @RequestBody CommentDto dto) {
        CommentDto comment = commentService.create(postId, dto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllByPost(@PathVariable(value = "postId") Long postId) {
        return commentService.getAllByPost(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> update(
            @PathVariable(value = "commentId") Long commentId,
            @PathVariable(value = "postId") Long postId,
            @RequestBody UpdateCommentDto dto) {
        CommentDto comment = commentService.update(postId, commentId, dto);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> delete(
            @PathVariable(value = "commentId") Long commentId,
            @PathVariable(value = "postId") Long postId) {
        commentService.delete(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
    }
}
