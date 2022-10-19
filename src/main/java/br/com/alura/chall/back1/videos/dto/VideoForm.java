package br.com.alura.chall.back1.videos.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import br.com.alura.chall.back1.videos.model.Categoria;
import br.com.alura.chall.back1.videos.model.Video;
import br.com.alura.chall.back1.videos.repository.CategoriaRepository;

public class VideoForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    @URL
    private String url;
    private Long categoriaId = 1l;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Video toVideo(CategoriaRepository categoriaRepository) {
        Video video = new Video();
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);
        video.setUrl(this.url);

        Optional<Categoria> optCategoria = categoriaRepository.findById(categoriaId);
        if (optCategoria.isPresent()){
            video.setCategoria(optCategoria.get());
        } else {
            video.setCategoria(categoriaRepository.findById(1l).get());
        }

        return video;
    }
}
