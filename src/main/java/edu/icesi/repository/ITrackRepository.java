package edu.icesi.repository;

import edu.icesi.model.Track;

import java.util.List;
import java.util.Optional;

public interface ITrackRepository {
    List<Track> findAll();
    Optional<Track> findById(String id);
    void create(Track track);
    void deleteById(String id);
}