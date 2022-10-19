package br.com.alura.chall.back1.videos.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.URL;

import br.com.alura.chall.back1.videos.model.Categoria;
import br.com.alura.chall.back1.videos.model.Video;
import br.com.alura.chall.back1.videos.repository.CategoriaRepository;

public class VideoPutForm {

    private String titulo;
    private String descricao;
    @URL
    private String url;
    private Long categoriaId;

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

    public void toVideo(Video video, CategoriaRepository categoriaRepository) {
        if (this.titulo != null)
            video.setTitulo(this.titulo);
        if (this.descricao != null)
            video.setDescricao(this.descricao);
        if (this.url != null)
            video.setUrl(this.url);
        if (this.categoriaId != null) {
            Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
            if (categoria.isPresent())
                video.setCategoria(categoria.get());
        }

    }

}
