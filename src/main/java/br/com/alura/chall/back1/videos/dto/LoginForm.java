package br.com.alura.chall.back1.videos.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toUPAT() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
