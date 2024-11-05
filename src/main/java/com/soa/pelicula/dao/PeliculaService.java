package com.soa.pelicula.dao;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.soa.pelicula.dto.Pelicula;

public interface PeliculaService {
    Page<Pelicula> getPelicula(String nombre, int page, int size);
    Pelicula guardarPelicula(Pelicula datosPelicula);
    Pelicula obtenerPeliculaID(Long id);
    Pelicula actualizarPelicula(Long id, Pelicula peliculaD);
    Map<String, Boolean> eliminarPelicula(Long id);
}
