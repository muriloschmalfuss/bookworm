package com.schmalfuss.bookworm.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    private String email;
    private String password;

    private String username;

    private String cpf;
}
