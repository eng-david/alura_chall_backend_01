package br.com.alura.chall.back1.videos.dto;

public class TokenDto {

    private String type;
    private String token;

    public TokenDto(String token, String type) {
        this.type = type;
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }
    
    
}
