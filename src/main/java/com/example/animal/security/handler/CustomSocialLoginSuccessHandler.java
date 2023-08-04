package com.example.animal.security.handler;

import com.example.animal.security.dto.MemberSecurityDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final PasswordEncoder passwordEncoder;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        String encodedPw = memberSecurityDTO.getPassword();

        // 소셜 로그인이고 패스워드가 1111
        if (memberSecurityDTO.isSocial()
        && (memberSecurityDTO.getPassword().equals("1111") || passwordEncoder.matches("1111", memberSecurityDTO.getPassword())))
        {
            response.sendRedirect("/member/modify");
            return;
        }else {
            response.sendRedirect("/board/list");
        }
    }
}
