package br.com.alura.chall.back1.videos.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.chall.back1.videos.dto.VideoDto;
import br.com.alura.chall.back1.videos.model.Video;
import br.com.alura.chall.back1.videos.repository.VideoRepository;

@RestController
public class VideosRest {

    @Autowired
    private VideoRepository videoRepository;

    // read all
    @GetMapping("/videos")
    public ResponseEntity<List<VideoDto>> getVideos() {
        List<Video> Videos = videoRepository.findAll();
        if (Videos.size() > 0) {
            List<VideoDto> VideosDto = VideoDto.toVideoDto(Videos);
            return ResponseEntity.ok(VideosDto);
        }
        return ResponseEntity.notFound().build();
    }

    // read specific by id
    @GetMapping("/videos/{id}")
    public ResponseEntity<VideoDto> getSpecificVideo(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            VideoDto videoDto = new VideoDto(video.get());
            return ResponseEntity.ok(videoDto);
        }
        return ResponseEntity.notFound().build();
    }

    // delete by id
    @DeleteMapping("/videos/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id){
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()){
            videoRepository.delete(video.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
