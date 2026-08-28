package edu.icesi.service.impl;

import edu.icesi.model.Artist;
import edu.icesi.repository.IArtistRepository;
import edu.icesi.service.IArtistService;

import java.util.List;
import java.util.UUID;


public class ArtistServiceImpl implements IArtistService {
    
    private final IArtistRepository artistRepository;

    public ArtistServiceImpl(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() { return artistRepository.findAll(); }

    @Override
    public void create(String name, String nationality) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Artist artist = new Artist(id, name, nationality);
        artistRepository.create(artist);
    }

    @Override
    public Artist findByNameWithTracks(String name) { return artistRepository.findByNameWithTracks(name); }

    @Override
    public void deleteById(String id) { artistRepository.deleteById(id); }
}