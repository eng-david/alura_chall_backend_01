package br.com.alura.chall.back1.videos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.chall.back1.videos.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
    
}
