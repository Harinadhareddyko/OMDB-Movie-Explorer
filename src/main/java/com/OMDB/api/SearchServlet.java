package com.OMDB.api;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/search")
public class SearchServlet extends HttpServlet {

    private OmdbService omdb = new OmdbService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String query = req.getParameter("query");
        String page = req.getParameter("page");

        if (query == null || query.isEmpty()) {
            resp.getWriter().write("{\"error\":\"query is required\"}");
            return;
        }

        int pageNum = (page == null) ? 1 : Integer.parseInt(page);

        // Create cache key
        String key = "search_" + query + "_" + pageNum;

        // Check cache
        String cached = CacheService.get(key);
        if (cached != null) {
            System.out.println("CACHE HIT: " + key);
            resp.getWriter().write(cached);
            return;
        }

        try {
            String json = omdb.searchMovie(query, pageNum);

            // Save to cache
            CacheService.save(key, json);

            resp.getWriter().write(json);
        } catch (Exception e) {
            resp.getWriter().write("{\"error\":\"Something went wrong\"}");
        }
    }
}
