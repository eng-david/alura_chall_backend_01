package br.com.alura.chall.back1.videos.api;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.chall.back1.videos.model.User;
import br.com.alura.chall.back1.videos.repository.UserRepository;
import br.com.alura.chall.back1.videos.service.AuthTokenService;

public class AuthenticationFilter extends OncePerRequestFilter {

    private AuthTokenService tokenService;

    private UserRepository userRepository;

    public AuthenticationFilter(AuthTokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getToken(request);
        boolean isValid = tokenService.isValid(token);
        if (isValid){
            authenticateClient(token);
        }    

        // go ahead
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        Long userId = tokenService.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}
