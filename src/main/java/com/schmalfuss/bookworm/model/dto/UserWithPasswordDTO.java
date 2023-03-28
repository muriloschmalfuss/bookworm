package com.schmalfuss.bookworm.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithPasswordDTO extends UserDTO{

    private String password;
}
