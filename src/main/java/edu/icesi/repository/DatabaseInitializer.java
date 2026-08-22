package edu.icesi.repository;

import edu.icesi.model.Artist;
import edu.icesi.model.Track;

public class DatabaseInitializer {
    
    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;

    public DatabaseInitializer(ArtistRepository artistRepository, TrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }


    public void initData() {
        for (int i = 1; i <= 10; i++) {
            Artist artist = new Artist("A" + i, "Artista " + i, "Colombia");
            artistRepository.create(artist);

            // Crear 5 tracks por cada artista (Total 50)
            for (int j = 1; j <= 5; j++) {
                String trackId = "T" + i + "-" + j;
                Track track = new Track(trackId, "Canción " + trackId, "Rock", 180, "Álbum " + i);

                // Establecer la relación Many-to-Many
                track.addArtist(artist);
                artist.addTrack(track);

                trackRepository.create(track);
            }
        }
        System.out.println("Base de datos inicializada: 10 Artistas y 50 Tracks creados exitosamente.");
    }
}