package br.com.alura.chall.back1.videos.dto;

import org.springframework.data.domain.Page;

import br.com.alura.chall.back1.videos.model.Video;

public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private CategoriaDto categoria;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.categoria = new CategoriaDto(video.getCategoria());
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

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }

    public static Page<VideoDto> toVideoDto(Page<Video> videos) {

        return videos.map(VideoDto::new);

    }

}
