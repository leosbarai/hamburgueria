package com.github.leosbarai.hamburgueria.user.parser;

import com.github.leosbarai.hamburgueria.user.dto.UserDTO;
import com.github.leosbarai.hamburgueria.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserParser {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.id = user.getId();
        dto.name = user.getName();
        dto.email = user.getEmail();
        dto.password = user.getPassword();
        dto.admin = user.isAdmin();

        return dto;
    }

    public User toEntity(UserDTO dto) {
        User entity = new User();

        entity.setName(dto.name);
        entity.setEmail(dto.email);
        entity.setPassword(dto.password);
        entity.setAdmin(dto.admin);

        return entity;
    }
}
