package edu.icesi.service;

import edu.icesi.model.Artist;

import java.util.List;

public interface IArtistService {
    List<Artist> findAll();
    void create(String name, String nationality);
    Artist findByNameWithTracks(String name);
    void deleteById(String id);
}