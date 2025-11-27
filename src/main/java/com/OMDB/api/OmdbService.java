package com.OMDB.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OmdbService {

    private static final String BASE_URL = "https://www.omdbapi.com/?apikey=";
    private static final String API_KEY = System.getenv("OMDB_API_KEY"); // secure

//    public String searchMovie(String query, int page) throws Exception {
//        String url = BASE_URL + API_KEY + "&s=" + query + "&page=" + page;
//        return sendGet(url);
//    }
    
    public String searchMovie(String query, int page) throws Exception {
        String url = BASE_URL + API_KEY + "&s=" + query + "&page=" + page;

//        System.out.println("API KEY = " + API_KEY);
//        System.out.println("Calling URL = " + url);

        return sendGet(url);
    }

    public String getMovieDetails(String imdbId) throws Exception {
        String url = BASE_URL + API_KEY + "&i=" + imdbId + "&plot=full";
        return sendGet(url);
    }

    private String sendGet(String urlStr) throws Exception {
        URL url = new URL(urlStr);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        return response.toString();
    }
}
