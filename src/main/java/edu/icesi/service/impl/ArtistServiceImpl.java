package edu.icesi.service.impl;

import edu.icesi.model.Artist;
import edu.icesi.repository.IArtistRepository;
import edu.icesi.service.IArtistService;

import java.util.List;
import java.util.Optional;

public class ArtistServiceImpl implements IArtistService {
    
    private final IArtistRepository artistRepository;

    // Inyección por constructor
    public ArtistServiceImpl(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() { return artistRepository.findAll(); }

    @Override
    public Artist create(String name, String nationality) {
        Artist artist = new Artist(null, name, nationality);
        artistRepository.create(artist);
        return artist;
    }

    @Override
    public Artist findByNameWithTracks(String name) {
        return artistRepository.findByNameWithTracks(name).orElse(null);
    }

    @Override
    public Artist findById(String id) {
        Optional<Artist> artistOpt = artistRepository.findById(id);
        if (artistOpt.isEmpty()) { return null; }
        return artistOpt.get();
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Artist> artistOpt = artistRepository.findById(id);
        if (artistOpt.isEmpty()) { return false; }
        Artist artist = artistOpt.get();
        
        if (!artist.getTracks().isEmpty()) {
            return false;
        }
        artistRepository.deleteById(id);
        return true;
    }
}