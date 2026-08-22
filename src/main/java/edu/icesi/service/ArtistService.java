package edu.icesi.service;

import edu.icesi.model.Artist;
import java.util.List;

public interface ArtistService {
    List<Artist> findAll();
    void create(Artist artist);
    Artist findByNameWithTracks(String name);
    void deleteById(String id);
}