package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.dto.TokenDTO;
import com.schmalfuss.bookworm.model.dto.UserDTO;
import com.schmalfuss.bookworm.model.dto.UserLoginDTO;
import com.schmalfuss.bookworm.model.dto.UserWithPasswordDTO;
import com.schmalfuss.bookworm.model.entity.UserEntity;
import com.schmalfuss.bookworm.model.mapper.UserMapper;
import com.schmalfuss.bookworm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO create(UserWithPasswordDTO userWithPasswordDTO) {
        UserEntity userEntity = userMapper.update(userWithPasswordDTO);
        userEntity = userRepository.save(userEntity);
        return userMapper.update(userEntity);
    }

    public TokenDTO login(UserLoginDTO userLoginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated(userLoginDTO.getUsername(), userLoginDTO.getPassword(), null);
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        if (auth.isAuthenticated()) {
            UserEntity userEntity = (UserEntity) auth.getPrincipal();
            String token = jwtService.generateToken(userMapper.update(userEntity));
            return new TokenDTO("Bearer", token);
        }

        throw new AuthenticationCredentialsNotFoundException("Credenciais inválidas.");
    }
}
