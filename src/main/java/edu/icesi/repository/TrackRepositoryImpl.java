package edu.icesi.repository;

import edu.icesi.model.Track;
import java.util.ArrayList;
import java.util.List;

public class TrackRepositoryImpl implements TrackRepository {
    
    private final List<Track> tracks = new ArrayList<>();

    @Override
    public List<Track> findAll() {
        return tracks;
    }

    @Override
    public void create(Track track) {
        tracks.add(track);
    }

    @Override
    public void deleteById(String id) {
        tracks.removeIf(t -> t.getId().equals(id));
    }
}