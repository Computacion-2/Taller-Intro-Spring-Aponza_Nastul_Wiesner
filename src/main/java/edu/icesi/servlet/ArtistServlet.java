package edu.icesi.servlet;

import edu.icesi.model.Artist;
import edu.icesi.service.IArtistService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet {

    private IArtistService artistService;

    @Override
    public void init() throws ServletException {
        super.init();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(edu.icesi.config.AppConfig.class);
        this.artistService = (IArtistService) context.getBean("artistServiceImpl");
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
            
            artistService.create(name, nationality);
            
        } else if ("delete".equals(action)) {
            String id = request.getParameter("id");
            artistService.deleteById(id);
        }
        response.sendRedirect(request.getContextPath() + "/artists");
    }
}