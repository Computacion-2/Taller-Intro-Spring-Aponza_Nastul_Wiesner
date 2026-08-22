package edu.icesi.repository;

import edu.icesi.model.Track;

import java.util.List;

public interface TrackRepository {
    List<Track> findAll();
    void create(Track track);
    void deleteById(String id);
}

