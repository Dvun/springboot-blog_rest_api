package com.springboot.blog.service.comment;


import com.springboot.blog.dto.comment.CommentDto;
import com.springboot.blog.dto.comment.UpdateCommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public CommentDto create(Long postId, CommentDto dto) {
        Post post = getPostById(postId);
        Comment comment = CommentMapper.INSTANCE.dtoToEntity(dto);
        comment.setPost(post);
        commentRepository.save(comment);
        return CommentMapper.INSTANCE.entityToDto(comment);
    }

    @Override
    public List<CommentDto> getAllByPost(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = getPostById(postId);
        Comment comment = getCommentById(commentId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post!");
        }
        return CommentMapper.INSTANCE.entityToDto(comment);
    }

    @Override
    public CommentDto update(Long postId, Long commentId, UpdateCommentDto dto) {
        Comment comment = getCommentById(commentId);
        Post post = getPostById(postId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post!");
        }
        BeanUtils.copyProperties(dto, comment);
        commentRepository.save(comment);
        return CommentMapper.INSTANCE.entityToDto(comment);
    }

    @Override
    public void delete(Long postId, Long commentId) {
        Post post = getPostById(postId);
        Comment comment = getCommentById(commentId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post!");
        }
        commentRepository.deleteById(commentId);
    }


    private Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    private Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }
}
