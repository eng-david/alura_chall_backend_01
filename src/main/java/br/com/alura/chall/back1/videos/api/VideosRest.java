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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.chall.back1.videos.dto.VideoDto;
import br.com.alura.chall.back1.videos.dto.VideoForm;
import br.com.alura.chall.back1.videos.dto.VideoPutForm;
import br.com.alura.chall.back1.videos.model.Video;
import br.com.alura.chall.back1.videos.repository.CategoriaRepository;
import br.com.alura.chall.back1.videos.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideosRest {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // read all
    @GetMapping
    public ResponseEntity<List<VideoDto>> getVideos() {
        List<Video> Videos = videoRepository.findAll();
        if (Videos.size() > 0) {
            List<VideoDto> VideosDto = VideoDto.toVideoDto(Videos);
            return ResponseEntity.ok(VideosDto);
        }
        return ResponseEntity.notFound().build();
    }

    // read by id
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getSpecificVideo(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            VideoDto videoDto = new VideoDto(video.get());
            return ResponseEntity.ok(videoDto);
        }
        return ResponseEntity.notFound().build();
    }

    // delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isPresent()) {
            videoRepository.delete(video.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // create
    @PostMapping
    public ResponseEntity<VideoDto> createVideo(@RequestBody @Valid VideoForm videoForm,
            UriComponentsBuilder uriBuilder) {
        Video video = videoForm.toVideo(categoriaRepository);
        videoRepository.save(video);
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    // update by id
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> updateVideo(@PathVariable Long id, @RequestBody @Valid VideoPutForm form) {
        Optional<Video> optVideo = videoRepository.findById(id);
        if (optVideo.isPresent()) {            
            form.toVideo(optVideo.get(), categoriaRepository);
            return ResponseEntity.ok(new VideoDto(optVideo.get()));
        }
        return ResponseEntity.notFound().build();

    }

    // search by video name
    @GetMapping("/")
    public ResponseEntity<List<VideoDto>> searchVideos(String search){

        List<Video> videos = videoRepository.findByTituloContainingIgnoreCase(search);

        if (videos.size() > 0){
            List<VideoDto> videosDto = VideoDto.toVideoDto(videos);
            return ResponseEntity.ok(videosDto);
        }

        return ResponseEntity.notFound().build();
    }

}
