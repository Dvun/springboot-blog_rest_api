package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.UpdatePostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostMapper;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.PostResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto create(PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        return PostMapper.INSTANCE.entityToDto(postRepository.save(post));
    }

    @Override
    public PostResponse getAll(int page, int size, String sort, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort), sortBy);
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostDto> content = posts.stream().map(PostMapper.INSTANCE::entityToDto).collect(Collectors.toList());
        return new PostResponse(
                content,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast()
        );
    }

    @Override
    public PostDto getById(Long id) {
        Post post = getEntityById(id);
        return PostMapper.INSTANCE.entityToDto(post);
    }

    @Override
    public PostDto update(Long id, UpdatePostDto dto) {
        Post post = getEntityById(id);
        BeanUtils.copyProperties(dto, post);
        postRepository.save(post);
        return PostMapper.INSTANCE.entityToDto(post);
    }

    @Override
    public void delete(Long id) {
        Post post = getEntityById(id);
        postRepository.deleteById(post.getId());
    }

    private Post getEntityById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
    }
}
