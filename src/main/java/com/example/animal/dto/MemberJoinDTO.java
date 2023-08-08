package com.example.animal.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberJoinDTO {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String name;
    private boolean del;
    private boolean sosicl;
}
