package br.com.alura.chall.back1.videos.dto;

import br.com.alura.chall.back1.videos.model.Categoria;

public class CategoriaPutForm {

    private String titulo;
    private String cor;

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void toCategoria(Categoria categoria) {

        if (this.titulo != null)
            categoria.setTitulo(this.titulo);
        if (this.cor != null)
            categoria.setCor(this.cor);

    }

}
