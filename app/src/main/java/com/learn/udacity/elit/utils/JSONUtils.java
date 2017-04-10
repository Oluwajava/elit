package com.learn.udacity.elit.utils;

import com.learn.udacity.elit.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayokun on 4/8/2017.
 */

public class JSONUtils {

    public static List<Movie> getJsonFromString(String response) {
        List<Movie> moviesList = new ArrayList<>();

        try {

            if (response != null) {
                JSONObject moviesJsonObject = new JSONObject(response);
                JSONArray movieData = moviesJsonObject.getJSONArray("results");

                for (int i = 0; i < movieData.length(); i++) {
                    JSONObject moviesObject = (JSONObject) movieData.get(i);
                    Movie movie = new Movie();
                    movie.setOriginalTitle(moviesObject.getString(Constants.Keys.ORIGINAL_TITLE));
                    movie.setAdult(moviesObject.getBoolean(Constants.Keys.ADULT));
                    movie.setBackdropPath(moviesObject.getString(Constants.Keys.BACKDROP_PATH));
                    movie.setId(moviesObject.getInt(Constants.Keys.ID));
                    movie.setOriginalLanguage(moviesObject.getString(Constants.Keys.ORIGINAL_LANGUAGE));
                    movie.setOverview(moviesObject.getString(Constants.Keys.OVERVIEW));
                    movie.setPopularity(moviesObject.getDouble(Constants.Keys.POPULARITY));
                    movie.setReleaseDate(moviesObject.getString(Constants.Keys.RELEASE_DATE));
                    movie.setPosterPath(moviesObject.getString(Constants.Keys.POSTER_PATH));
                    movie.setVideo(moviesObject.getBoolean(Constants.Keys.VIDEO));
                    movie.setVoteAverage(moviesObject.getInt(Constants.Keys.VOTE_AVERAGE));
                    movie.setVoteCount(moviesObject.getInt(Constants.Keys.VOTE_COUNT));

                    moviesList.add(movie);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return moviesList;
    }
}
