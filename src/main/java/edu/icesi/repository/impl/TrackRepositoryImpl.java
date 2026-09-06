package edu.icesi.repository.impl;

import edu.icesi.model.Track;
import edu.icesi.repository.ITrackRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class TrackRepositoryImpl implements ITrackRepository {
    
    private final List<Track> tracks = new ArrayList<>();

    @Override
    public List<Track> findAll() {
        return new ArrayList<>(tracks);
    }

    @Override
    public Optional<Track> findById(String id) {
        return tracks.stream()
                .filter(track -> track.getId().equals(id))
                .findFirst();
    }

    @Override
    public void create(Track track) {
        if (track.getId() == null) {
            track.setId(UUID.randomUUID().toString().substring(0, 8));
        }
        tracks.add(track);
    }

    @Override
    public void deleteById(String id) {
        tracks.removeIf(track -> track.getId().equals(id));
    }
}