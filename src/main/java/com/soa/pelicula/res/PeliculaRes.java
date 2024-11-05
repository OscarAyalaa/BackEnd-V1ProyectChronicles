package com.soa.pelicula.res;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soa.pelicula.dao.PeliculaService;
import com.soa.pelicula.dto.Pelicula;
import com.soa.pelicula.dto.ResponsePelicula;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class PeliculaRes {
    
    private final PeliculaService peliculaService;
    
    @GetMapping("/peliculas")
    public ResponseEntity<ResponsePelicula> getPelicula(@RequestParam Optional<String> name,
                                                        @RequestParam Optional<Integer> page,
                                                        @RequestParam Optional<Integer> size) throws InterruptedException{
        return ResponseEntity.ok().body(
                ResponsePelicula.builder()
                .message("Multimedias....")
                .data(Map.of("page", peliculaService.getPelicula(name.orElse(""), page.orElse(0), size.orElse(10))))
                .build());
    }
    
    @PostMapping("/peliculas")
    public ResponseEntity<Pelicula> guardarPelicula(@RequestBody Pelicula pelicula){
        return new ResponseEntity<Pelicula>(peliculaService.guardarPelicula(pelicula), HttpStatus.OK);
    }
    
    @GetMapping("/peliculas/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaID(@PathVariable Long id){
        return new ResponseEntity<Pelicula>(peliculaService.obtenerPeliculaID(id), HttpStatus.OK);
    }
    
    @PutMapping("/peliculas/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula peliculaD){
        return new ResponseEntity<Pelicula>(peliculaService.actualizarPelicula(id, peliculaD), HttpStatus.OK);
    }
    
    @DeleteMapping("/peliculas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPelicula(@PathVariable Long id){
        return new ResponseEntity<Map<String,Boolean>>(peliculaService.eliminarPelicula(id), HttpStatus.OK);
    }
    
}
