package com.soa.pelicula.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soa.pelicula.dto.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    Page<Pelicula> findByNombreContaining(String nombre, Pageable pageable);
}
