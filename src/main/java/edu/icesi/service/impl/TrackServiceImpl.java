package edu.icesi.service.impl;

import edu.icesi.model.Artist;
import edu.icesi.model.Track;
import edu.icesi.repository.ITrackRepository;
import edu.icesi.service.ITrackService;
import edu.icesi.service.IArtistService;

import java.util.List;
import java.util.Optional;

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
    public Track create(String title, String genre, int duration, String albumTitle, String[] artistIds) {
        Track newTrack = new Track(null, title, genre, duration, albumTitle);

        if (artistIds != null) {
            for (String artistId : artistIds) {
                Artist artist = artistService.findById(artistId);
                if (artist != null) {
                    newTrack.addArtist(artist);
                    artist.addTrack(newTrack);
                }
            }
        }
        trackRepository.create(newTrack);
        return newTrack;
    }

    @Override
    public void deleteById(String id) {
        Optional<Track> trackOpt = trackRepository.findById(id);
        if (trackOpt.isEmpty()) return;
        Track track = trackOpt.get();
        track.getArtists().forEach(artist -> artist.getTracks().remove(track));
        trackRepository.deleteById(id);
    }
}