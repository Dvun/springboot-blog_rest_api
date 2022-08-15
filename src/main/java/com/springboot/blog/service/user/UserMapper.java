package com.springboot.blog.service.user;

import com.springboot.blog.dto.user.RegisterDto;
import com.springboot.blog.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    RegisterDto dtoToEntity(User user);
    User entityToDto(RegisterDto dto);

}
