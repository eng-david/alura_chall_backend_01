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

import br.com.alura.chall.back1.videos.dto.CategoriaDto;
import br.com.alura.chall.back1.videos.dto.CategoriaForm;
import br.com.alura.chall.back1.videos.dto.CategoriaPutForm;
import br.com.alura.chall.back1.videos.dto.VideoDto;
import br.com.alura.chall.back1.videos.model.Categoria;
import br.com.alura.chall.back1.videos.model.Video;
import br.com.alura.chall.back1.videos.repository.CategoriaRepository;
import br.com.alura.chall.back1.videos.repository.VideoRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriasRest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videoRepository;

    // read all
    @GetMapping
    public ResponseEntity<List<CategoriaDto>> getCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.size() > 0) {
            List<CategoriaDto> categoriaDto = CategoriaDto.toCategoriaDto(categorias);
            return ResponseEntity.ok(categoriaDto);
        }
        return ResponseEntity.notFound().build();
    }

    // read by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getSpecificCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            CategoriaDto categoriaDto = new CategoriaDto(categoria.get());
            return ResponseEntity.ok(categoriaDto);
        }
        return ResponseEntity.notFound().build();
    }

    // delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.delete(categoria.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // create
    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody @Valid CategoriaForm categoriaForm,
            UriComponentsBuilder uriBuilder) {
        Categoria categoria = categoriaForm.toCategoria();
        categoriaRepository.save(categoria);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    // update by id
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable Long id,
            @RequestBody @Valid CategoriaPutForm form) {
        Optional<Categoria> optCategoria = categoriaRepository.findById(id);
        if (optCategoria.isPresent()) {
            form.toCategoria(optCategoria.get());
            return ResponseEntity.ok(new CategoriaDto(optCategoria.get()));
        }
        return ResponseEntity.notFound().build();

    }

    // read videos by categoria id
    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoDto>> getSpecificCategoriaVideos(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            List<Video> videos = videoRepository.findByCategoria(categoria.get());
            if(videos.size() > 0){
                List<VideoDto> videosDto = VideoDto.toVideoDto(videos);
                return ResponseEntity.ok(videosDto);    
            }
        }
        return ResponseEntity.notFound().build();
    }

}
