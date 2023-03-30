package com.schmalfuss.bookworm.filter;

import com.schmalfuss.bookworm.model.entity.UserEntity;
import com.schmalfuss.bookworm.service.JwtService;
import com.schmalfuss.bookworm.service.LoginUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomJwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginUserService loginUserService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader("authorization");
        if (token != null && !token.isBlank() && token.contains("Bearer ")) {
            token = token.trim().replace("Bearer ", "");
            if (jwtService.validateToken(token)) {
                String username = jwtService.getUsername(token);
                UserEntity user = (UserEntity) loginUserService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated(user.getUsername(), null, user.getAuthorities());
                if(SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
