package edu.icesi.servlet;

import edu.icesi.config.SpringContextManager;
import edu.icesi.service.IArtistService;
import edu.icesi.service.ITrackService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet("/tracks")
public class TrackServlet extends HttpServlet {

    private ITrackService trackService;
    private IArtistService artistService;

    @Override
    public void init() throws ServletException {
        super.init();
        AnnotationConfigApplicationContext context = SpringContextManager.getContext();
        this.trackService = (ITrackService) context.getBean("trackService");
        this.artistService = (IArtistService) context.getBean("artistService");
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
            String[] artistIds = request.getParameterValues("artistIds");
            
            trackService.create(title, genre, duration, albumTitle, artistIds);
            
        } else if ("delete".equals(action)) {
            trackService.deleteById(request.getParameter("id"));
        }

        response.sendRedirect(request.getContextPath() + "/tracks");
    }
}