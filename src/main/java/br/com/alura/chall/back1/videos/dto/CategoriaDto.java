package br.com.alura.chall.back1.videos.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.chall.back1.videos.model.Categoria;

public class CategoriaDto {

    private Long id;
    private String titulo;
    private String cor;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    
    public static List<CategoriaDto> toCategoriaDto(List<Categoria> categoria) {

        List<CategoriaDto> categoriasDto = new ArrayList<>();

        categoria.forEach(c -> {
            categoriasDto.add(new CategoriaDto(c));
        });

        return categoriasDto;

    }

}
