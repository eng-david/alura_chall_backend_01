package br.com.alura.chall.back1.videos.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.chall.back1.videos.dto.VideoDto;
import br.com.alura.chall.back1.videos.dto.VideoForm;
import br.com.alura.chall.back1.videos.dto.VideoPutForm;
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

    // read by id
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
    public ResponseEntity<?> deleteVideo(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            videoRepository.delete(video.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // create
    @PostMapping("/videos")
    public ResponseEntity<VideoDto> createVideo(@RequestBody @Valid VideoForm videoForm,
            UriComponentsBuilder uriBuilder) {
        Video video = videoForm.toVideo();
        videoRepository.save(video);
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    // update by id
    @PutMapping("/videos/{id}")
    @Transactional
    public ResponseEntity<VideoDto> updateVideo(@PathVariable Long id, @RequestBody @Valid VideoPutForm form) {
        Optional<Video> optVideo = videoRepository.findById(id);
        if (optVideo.isPresent()) {
            Video video = optVideo.get();
            video.setTitulo(form.getTitulo());
            video.setDescricao(form.getDescricao());
            video.setUrl(form.getUrl());

            return ResponseEntity.ok(new VideoDto(video));
        }
        return ResponseEntity.notFound().build();

    }

}
