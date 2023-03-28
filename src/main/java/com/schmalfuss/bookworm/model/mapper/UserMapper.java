package com.schmalfuss.bookworm.model.mapper;

import com.schmalfuss.bookworm.model.dto.UserDTO;
import com.schmalfuss.bookworm.model.dto.UserWithPasswordDTO;
import com.schmalfuss.bookworm.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder encoder;

    public UserEntity update(UserWithPasswordDTO userWithPasswordDTO) {
        UserEntity user = new UserEntity();
        user.setCpf(userWithPasswordDTO.getCpf());
        user.setEmail(userWithPasswordDTO.getEmail());
        user.setName(userWithPasswordDTO.getName());
        user.setUsername(userWithPasswordDTO.getUsername());
        user.setPassword(encoder.encode(userWithPasswordDTO.getPassword()));

        return user;
    }

    public UserDTO update(UserEntity userEntity) {
        UserDTO user = new UserDTO();
        user.setCpf(userEntity.getCpf());
        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());
        user.setUsername(userEntity.getUsername());

        return user;
    }
}
