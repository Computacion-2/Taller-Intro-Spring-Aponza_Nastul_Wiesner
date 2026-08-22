package edu.icesi.service;

import edu.icesi.model.Track;
import edu.icesi.repository.TrackRepository;
import java.util.List;

public class TrackServiceImpl implements TrackService {
    
    private final TrackRepository trackRepository;

    // by constructor
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public List<Track> findAll() { return trackRepository.findAll(); }

    @Override
    public void create(Track track) { trackRepository.create(track); }

    @Override
    public void deleteById(String id) { trackRepository.deleteById(id); }
}