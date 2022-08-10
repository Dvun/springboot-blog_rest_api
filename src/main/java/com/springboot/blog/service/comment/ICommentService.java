package com.springboot.blog.service.comment;

import com.springboot.blog.dto.comment.CommentDto;

import java.util.List;

public interface ICommentService {

    CommentDto create(Long postId, CommentDto dto);
    List<CommentDto> getAllByPost(Long postId);
    CommentDto getCommentById(Long postId, Long commentId);

}
