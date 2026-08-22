package edu.icesi.repository;

import edu.icesi.model.Artist;

import java.util.List;

public interface ArtistRepository {
    List<Artist> findAll();
    void create(Artist artist);
    Artist findByNameWithTracks(String name);
    void deleteById(String id);
}
