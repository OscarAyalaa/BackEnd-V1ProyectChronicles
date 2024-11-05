package com.soa.pelicula.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.soa.ExceptionHandler.ResourceNotFoundPelicula;
import com.soa.pelicula.dto.Pelicula;
import com.soa.pelicula.repository.PeliculaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PeliculaServiceImpl implements PeliculaService{
    
    private final PeliculaRepository peliculaRepository;

    @Override
    public Page<Pelicula> getPelicula(String nombre, int page, int size) {
        log.info("Fetching users for page {} of size {}", page, size);
        return peliculaRepository.findByNombreContaining(nombre, PageRequest.of(page, size));
    }

    @Override
    public Pelicula guardarPelicula(Pelicula datosPelicula) {
        return peliculaRepository.save(datosPelicula);
    }

    @Override
    public Pelicula obtenerPeliculaID(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundPelicula("No se encontro pelicula con el ID: "+id));
        return pelicula;
    }

    @Override
    public Pelicula actualizarPelicula(Long id, Pelicula peliculaD) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundPelicula("No se encontro pelicula con el ID: "+id));
        
        pelicula.setNombre(peliculaD.getNombre());
        pelicula.setGenero(peliculaD.getGenero());
        pelicula.setAnio(peliculaD.getAnio());
        pelicula.setDireccion(peliculaD.getDireccion());
        pelicula.setMultimedia(peliculaD.getMultimedia());
        pelicula.setPortada(peliculaD.getPortada());
        
        Pelicula peliculaActualizada = peliculaRepository.save(pelicula);
        return peliculaActualizada;
    }

    @Override
    public Map<String, Boolean> eliminarPelicula(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundPelicula("No se encontro pelicula con el ID: "+id));
        
        peliculaRepository.delete(pelicula);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        
        return respuesta;
    }
    
}
