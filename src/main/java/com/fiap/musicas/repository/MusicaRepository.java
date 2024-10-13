package com.fiap.musicas.repository;

import com.fiap.musicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    List<Musica> findByNomeContainingIgnoreCase(String nome);

}
