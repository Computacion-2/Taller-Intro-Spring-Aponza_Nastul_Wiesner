package edu.icesi.servlet;

import edu.icesi.service.IArtistService;
import edu.icesi.service.ITrackService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@WebServlet("/tracks")
public class TrackServlet extends HttpServlet {

    private ITrackService trackService;
    private IArtistService artistService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        trackService = (ITrackService) context.getBean("trackServiceBean");
        artistService = (IArtistService) context.getBean("artistServiceBean");
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
            
            trackService.create(title, genre, duration, albumTitle, artistIds);
            
        } else if ("delete".equals(action)) {
            trackService.deleteById(request.getParameter("id"));
        }

        response.sendRedirect(request.getContextPath() + "/tracks");
    }
}