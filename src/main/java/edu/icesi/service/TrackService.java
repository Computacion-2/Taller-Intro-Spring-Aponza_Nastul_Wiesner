package edu.icesi.service;

import edu.icesi.model.Track;
import java.util.List;

public interface TrackService {
    List<Track> findAll();
    void create(Track track);
    void deleteById(String id);
}