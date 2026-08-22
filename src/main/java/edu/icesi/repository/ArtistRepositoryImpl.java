package edu.icesi.repository;

import edu.icesi.model.Artist;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepository {
    
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