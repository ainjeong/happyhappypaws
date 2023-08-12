package com.example.animal.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity{
    @Id
    private String id;

    private String email;

    private String password;

    private String name;

    private boolean del;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void chagePassword(String password){
        this.password = password;
    }
    public void changeEmail(String email){
        this.email = email;
    }
    public void changeDel(boolean del){
        this.del = del;
    }
    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles(){
        this.roleSet.clear();
    }



}
