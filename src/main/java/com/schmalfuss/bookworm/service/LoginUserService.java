package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.entity.UserEntity;
import com.schmalfuss.bookworm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOp = Optional.ofNullable(userRepository.findByUsername(username));

        if (userEntityOp.isPresent()) {
            return userEntityOp.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
