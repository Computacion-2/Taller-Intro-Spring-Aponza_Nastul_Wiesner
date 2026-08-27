package edu.icesi.repository.impl;

import edu.icesi.model.Track;
import edu.icesi.repository.ITrackRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TrackRepositoryImpl implements ITrackRepository {
    
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