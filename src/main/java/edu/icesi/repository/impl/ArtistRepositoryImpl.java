package edu.icesi.repository.impl;

import edu.icesi.model.Artist;
import edu.icesi.repository.IArtistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ArtistRepositoryImpl implements IArtistRepository {
    
    private final List<Artist> artists = new ArrayList<>();

    @Override
    public List<Artist> findAll() {
        return new ArrayList<>(artists);
    }

    @Override
    public void create(Artist artist) {
        if (artist.getId() == null) {
            artist.setId(UUID.randomUUID().toString().substring(0, 8));
        }
        artists.add(artist);
    }

    @Override
    public Optional<Artist> findById(String id) {
        return artists.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Artist> findByNameWithTracks(String name) {
        return artists.stream()
            .filter(a -> a.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    @Override
    public void deleteById(String id) {
        artists.removeIf(a -> a.getId().equals(id));
    }
}