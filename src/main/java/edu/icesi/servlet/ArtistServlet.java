package edu.icesi.servlet;

import edu.icesi.model.Artist;
import edu.icesi.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet {

    private ArtistService artistService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        artistService = (ArtistService) context.getBean("artistServiceBean");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("search".equals(action)) {
            String name = request.getParameter("name");
            Artist artist = artistService.findByNameWithTracks(name);
            request.setAttribute("artist", artist);
            request.getRequestDispatcher("/WEB-INF/artist_detail.jsp").forward(request, response);
        } else {
            List<Artist> artists = artistService.findAll();
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("/WEB-INF/artists.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String name = request.getParameter("name");
            String nationality = request.getParameter("nationality");
            String id = UUID.randomUUID().toString().substring(0, 8);
            
            Artist newArtist = new Artist(id, name, nationality);
            artistService.create(newArtist);
            
        } else if ("delete".equals(action)) {
            String id = request.getParameter("id");
            artistService.deleteById(id);
        }
        response.sendRedirect(request.getContextPath() + "/artists");
    }
}