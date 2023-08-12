package com.example.animal.security.handler;

import com.example.animal.domain.Member;
import com.example.animal.repository.MemberRepository;
import com.example.animal.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : " + username);

        Optional<Member> result = memberRepository.getWithRoles(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("username not found...");
        }
        Member member = result.get();

        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(
                        member.getId(),
                        member.getPassword(),
                        member.getEmail(),
                        member.isDel(),
                        member.getRoleSet()
                                .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                                .collect(Collectors.toList())
                );

        return memberSecurityDTO;
    }
}
