package com.mega.amps.mapper;

import com.mega.amps.domain.User;
import com.mega.amps.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> user);

    @Mapping(source = "fullname", target = "fullname")
    @Mapping(source = "role", target = "role")
    User toUser(UserDTO userDTO);

}
