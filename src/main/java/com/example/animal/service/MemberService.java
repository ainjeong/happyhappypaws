package com.example.animal.service;

import com.example.animal.domain.Member;
import com.example.animal.dto.MemberJoinDTO;

public interface MemberService {
    void deleteMember(String id);

    static class MidExistException extends Exception{

    }
    void join(MemberJoinDTO memberJoinDTO)throws MidExistException;

    Member findById(String id);
}
