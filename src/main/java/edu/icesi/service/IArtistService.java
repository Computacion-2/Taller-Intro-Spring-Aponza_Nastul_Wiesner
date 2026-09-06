package edu.icesi.service;

import edu.icesi.model.Artist;
import java.util.List;

public interface IArtistService {
    List<Artist> findAll();
    Artist create(String name, String nationality);
    Artist findByNameWithTracks(String name);
    Artist findById(String id);
    boolean deleteById(String id);
}