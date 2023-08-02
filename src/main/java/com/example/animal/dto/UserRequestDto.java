package com.example.animal.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;

    private String password;

    private String name;
}
