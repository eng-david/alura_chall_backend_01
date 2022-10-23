package br.com.alura.chall.back1.videos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.chall.back1.videos.model.Categoria;
import br.com.alura.chall.back1.videos.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Page<Video> findByCategoria(Categoria categoria, Pageable pageable);

    //List<Video> findByTitulo(String search);

    Page<Video> findByTituloContainingIgnoreCase(String search, Pageable pageable);
    
}
