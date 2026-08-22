package edu.icesi.servlet;

import edu.icesi.model.Artist;
import edu.icesi.model.Track;
import edu.icesi.service.ArtistService;
import edu.icesi.service.TrackService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/tracks")
public class TrackServlet extends HttpServlet {

    private TrackService trackService;
    private ArtistService artistService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        trackService = (TrackService) context.getBean("trackServiceBean");
        artistService = (ArtistService) context.getBean("artistServiceBean");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tracks", trackService.findAll());
        request.setAttribute("artists", artistService.findAll()); 
        request.getRequestDispatcher("/WEB-INF/tracks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String title = request.getParameter("title");
            String genre = request.getParameter("genre");
            int duration = Integer.parseInt(request.getParameter("duration"));
            String albumTitle = request.getParameter("albumTitle");
            String[] artistIds = request.getParameterValues("artistIds"); // Capturamos las selecciones múltiples
            
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
            trackService.create(newTrack);
            
        } else if ("delete".equals(action)) {
            trackService.deleteById(request.getParameter("id"));
        }

        response.sendRedirect(request.getContextPath() + "/tracks");
    }
}