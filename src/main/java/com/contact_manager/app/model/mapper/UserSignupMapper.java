package com.contact_manager.app.model.mapper;

import com.contact_manager.app.model.entities.User;
import com.contact_manager.app.model.request.UserSignupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserSignupMapper {

    @Mapping(target = "password", ignore = true)
    UserSignupDto entityToDto(User user);

    @Mapping(target = "roles", ignore = true)
    User dtoToEntity(UserSignupDto userSignupDto);
}
