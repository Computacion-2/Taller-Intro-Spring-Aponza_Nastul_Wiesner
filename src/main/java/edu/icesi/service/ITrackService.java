package edu.icesi.service;

import edu.icesi.model.Track;
import java.util.List;

public interface ITrackService {
    List<Track> findAll();
    void create(String title, String genre, int duration, String albumTitle, String[] artistIds);
    void deleteById(String id);
}