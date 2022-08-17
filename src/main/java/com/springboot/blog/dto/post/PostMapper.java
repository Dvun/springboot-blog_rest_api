package com.springboot.blog.dto.post;

import com.springboot.blog.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "post.comments", ignore = true)
    PostDto entityToDto(Post post);
    Post dtoToEntity(PostDto dto);
}
