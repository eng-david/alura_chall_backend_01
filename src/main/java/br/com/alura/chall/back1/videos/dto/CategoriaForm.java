package br.com.alura.chall.back1.videos.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.chall.back1.videos.model.Categoria;

public class CategoriaForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String cor;

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria toCategoria() {
        Categoria categoria = new Categoria();
        categoria.setTitulo(this.titulo);
        categoria.setCor(this.cor);

        return categoria;
    }

}
