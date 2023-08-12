package com.example.animal.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {
    private String id;
    private String password;
    private String email;
    private boolean del;
    public Map<String, Object> props;


    public MemberSecurityDTO(String username, String password, String email, boolean del, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = username;
        this.password = password;
        this.email = email;
        this.del = del;
    }


}
