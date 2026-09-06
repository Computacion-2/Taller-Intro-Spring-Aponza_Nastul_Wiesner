package edu.icesi.repository;

import edu.icesi.model.Artist;

import java.util.List;
import java.util.Optional;

public interface IArtistRepository {
    List<Artist> findAll();
    void create(Artist artist);
    Optional<Artist> findById(String id);
    Optional<Artist> findByNameWithTracks(String name);
    void deleteById(String id);
}
