package com.example.animal.dto;

import lombok.Data;

@Data
public class MemberJoinDTO {
    private String id;
    private String password;
    private String email;
    private String name;
    private boolean del;
    private boolean sosicl;
}
