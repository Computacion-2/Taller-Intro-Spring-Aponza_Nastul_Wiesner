package edu.icesi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.icesi.repository.DatabaseInitializer;
import edu.icesi.repository.IArtistRepository;
import edu.icesi.repository.ITrackRepository;
import edu.icesi.repository.impl.ArtistRepositoryImpl;
import edu.icesi.repository.impl.TrackRepositoryImpl;
import edu.icesi.service.IArtistService;
import edu.icesi.service.ITrackService;
import edu.icesi.service.impl.ArtistServiceImpl;
import edu.icesi.service.impl.TrackServiceImpl;

@Configuration
@ComponentScan(basePackages = "edu.icesi")
public class AppConfig {
    

    @Bean
    public IArtistRepository artistRepository() {
        return new ArtistRepositoryImpl();
    }

    @Bean
    public IArtistService artistService(){
        return new ArtistServiceImpl(artistRepository());
    }
    
    @Bean
    public ITrackRepository trackRepository(){
        return new TrackRepositoryImpl(); 
    }

    @Bean
    public ITrackService trackService(){
        return new TrackServiceImpl(trackRepository(), artistService());
    }

    @Bean
    public DatabaseInitializer databaseInitializer(){
        return new DatabaseInitializer(artistRepository(), trackRepository());
    }
}