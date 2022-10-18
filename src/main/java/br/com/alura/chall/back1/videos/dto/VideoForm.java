package br.com.alura.chall.back1.videos.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import br.com.alura.chall.back1.videos.model.Video;

public class VideoForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank @URL
    private String url;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Video toVideo() {
        Video video = new Video();
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);
        video.setUrl(this.url);

        return video;
    }

}
