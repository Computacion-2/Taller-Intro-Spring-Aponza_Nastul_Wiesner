package edu.icesi.service.impl;

import edu.icesi.model.Artist;
import edu.icesi.model.Track;
import edu.icesi.repository.ITrackRepository;
import edu.icesi.service.ITrackService;
import edu.icesi.service.IArtistService;

import java.util.List;
import java.util.UUID;

public class TrackServiceImpl implements ITrackService {
    
    private final ITrackRepository trackRepository;
    private final IArtistService artistService;

    // by constructor
    public TrackServiceImpl(ITrackRepository trackRepository, IArtistService artistService) {
        this.trackRepository = trackRepository;
        this.artistService = artistService;
    }

    @Override
    public List<Track> findAll() { return trackRepository.findAll(); }

    @Override
    public void create(String title, String genre, int duration, String albumTitle, String[] artistIds) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Track newTrack = new Track(id, title, genre, duration, albumTitle);

        if (artistIds != null) {
            for (String artistId : artistIds) {
                Artist artist = artistService.findAll().stream()
                        .filter(a -> a.getId().equals(artistId)).findFirst().orElse(null);
                if (artist != null) {
                    newTrack.addArtist(artist);
                    artist.addTrack(newTrack);
                }
            }
        }
        trackRepository.create(newTrack);
    }

    @Override
    public void deleteById(String id) { trackRepository.deleteById(id); }
}