package br.com.alura.chall.back1.videos.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.chall.back1.videos.dto.LoginForm;
import br.com.alura.chall.back1.videos.dto.TokenDto;
import br.com.alura.chall.back1.videos.service.AuthTokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthTokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken authData = form.toUPAT();
        
        try {
            Authentication authentication = authenticationManager.authenticate(authData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, ""));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
    
}
