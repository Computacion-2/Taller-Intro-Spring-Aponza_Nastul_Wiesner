package edu.icesi.service;

import edu.icesi.model.Artist;
import edu.icesi.repository.ArtistRepository;
import java.util.List;

public class ArtistServiceImpl implements ArtistService {
    
    private final ArtistRepository artistRepository;

    // Inyección por constructor
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() { return artistRepository.findAll(); }

    @Override
    public void create(Artist artist) { artistRepository.create(artist); }

    @Override
    public Artist findByNameWithTracks(String name) { return artistRepository.findByNameWithTracks(name); }

    @Override
    public void deleteById(String id) { artistRepository.deleteById(id); }
}