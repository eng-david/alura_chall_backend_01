package br.com.alura.chall.back1.videos.api;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@Transactional
public class VideosRest {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // read all
    @GetMapping
    public ResponseEntity<Page<VideoDto>> getVideos(@PageableDefault(size = 5) Pageable pageable) {
        Page<Video> Videos = videoRepository.findAll(pageable);
        if (Videos.getTotalElements() > 0) {
            Page<VideoDto> VideosDto = VideoDto.toVideoDto(Videos);
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
    public ResponseEntity<VideoDto> createVideo(@RequestBody @Valid VideoForm form,
            UriComponentsBuilder uriBuilder) {
        Video video = form.toVideo(categoriaRepository);
        videoRepository.save(video);
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    // update by id
    @PutMapping("/{id}")
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
    public ResponseEntity<Page<VideoDto>> searchVideos(@PageableDefault(size = 5) Pageable pageable, String search){

        Page<Video> videos = videoRepository.findByTituloContainingIgnoreCase(search, pageable);

        if (videos.getTotalElements() > 0){
            Page<VideoDto> videosDto = VideoDto.toVideoDto(videos);
            return ResponseEntity.ok(videosDto);
        }

        return ResponseEntity.notFound().build();
    }

}
