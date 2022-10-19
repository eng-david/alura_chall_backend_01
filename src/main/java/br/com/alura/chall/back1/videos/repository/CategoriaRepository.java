package br.com.alura.chall.back1.videos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.chall.back1.videos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
