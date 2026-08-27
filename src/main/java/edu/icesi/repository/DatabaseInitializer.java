package edu.icesi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.icesi.model.Artist;
import edu.icesi.model.Track;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
    
    private final IArtistRepository artistRepository;
    private final ITrackRepository trackRepository;

    @Autowired
    public DatabaseInitializer(IArtistRepository artistRepository, ITrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i <= 10; i++) {
            Artist artist = new Artist("A" + i, "Artista " + i, "Colombia");
            artistRepository.create(artist);
            for (int j = 1; j <= 5; j++) {
                String trackId = "T" + i + "-" + j;
                Track track = new Track(trackId, "Canción " + trackId, "Rock", 180, "Álbum " + i);

                track.addArtist(artist);
                artist.addTrack(track);

                trackRepository.create(track);
            }
        }
        System.out.println("Base de datos inicializada: 10 Artistas y 50 Tracks creados exitosamente.");
    }
}