package com.OMDB.api;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/movie")
public class MovieServelet extends HttpServlet {

    private OmdbService omdb = new OmdbService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	

    	 resp.setContentType("application/json");
    	    resp.setCharacterEncoding("UTF-8");

    	    String imdbId = req.getParameter("id");

    	    if (imdbId == null || imdbId.isEmpty()) {
    	        resp.getWriter().write("{\"error\":\"id is required\"}");
    	        return;
    	    }

    	    String key = "movie_" + imdbId;

    	    // Cache check
    	    String cached = CacheService.get(key);
    	    if (cached != null) {
    	        System.out.println("CACHE HIT: " + key);
    	        resp.getWriter().write(cached);
    	        return;
    	    }

    	    try {
    	        String json = omdb.getMovieDetails(imdbId);

    	        CacheService.save(key, json);

    	        resp.getWriter().write(json);
    	    } catch (Exception e) {
    	        resp.getWriter().write("{\"error\":\"Something went wrong\"}");
    	    }
    	}
}
