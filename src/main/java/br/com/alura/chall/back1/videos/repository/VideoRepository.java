package br.com.alura.chall.back1.videos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.chall.back1.videos.model.Categoria;
import br.com.alura.chall.back1.videos.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByCategoria(Categoria categoria);

    List<Video> findByTitulo(String search);

    List<Video> findByTituloContainingIgnoreCase(String search);
    
}
