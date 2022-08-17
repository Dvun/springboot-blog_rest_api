package com.springboot.blog.dto.user;

import com.springboot.blog.entity.User;
import com.springboot.blog.security.service.UserDetailsImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    RegisterDto dtoToEntity(User user);
    User entityToDto(RegisterDto dto);
    @Mapping(target = "user.roles", ignore = true)
    UserDto userDetailsToUserDto(UserDetailsImpl user);

}
