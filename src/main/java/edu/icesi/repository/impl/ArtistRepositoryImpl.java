package edu.icesi.repository.impl;

import edu.icesi.model.Artist;
import edu.icesi.repository.IArtistRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepositoryImpl implements IArtistRepository {
    
    private final List<Artist> artists = new ArrayList<>();

    @Override
    public List<Artist> findAll() {
        return artists;
    }

    @Override
    public void create(Artist artist) {
        artists.add(artist);
    }

    @Override
    public Artist findByNameWithTracks(String name) {
        return artists.stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void deleteById(String id) {
        artists.removeIf(a -> a.getId().equals(id));
    }
}