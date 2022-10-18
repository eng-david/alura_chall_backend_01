package br.com.alura.chall.back1.videos.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.chall.back1.videos.model.Video;

public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static List<VideoDto> toVideoDto(List<Video> Video) {

        List<VideoDto> videosDto = new ArrayList<>();

        Video.forEach(v -> {

            // VideoDto dto = new VideoDto();

            // dto.setTitulo(v.getTitulo());
            // dto.setDescricao(v.getDescricao());
            // dto.setUrl(v.getUrl());

            // VideosDto.add(dto);

            videosDto.add(new VideoDto(v));
        });

        return videosDto;

    }

}
