package com.learn.udacity.elit.utils;

/**
 * Created by Mayokun on 4/8/2017.
 */

public class Constants {

    public interface Endpoints {
        String BASE_URL = "http://api.themoviedb.org/3/movie";
        String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";
        String POPULAR_URL = BASE_URL+"/popular";
        String TOP_RATED_URL = BASE_URL+"/top_rated";
    }

    public interface Keys {
        String API_KEY = "api_key";
        String MY_API_KEY = "8b33f5b57c1964517ae7a3229bedbf88";

        String RESULTS = "results";
        String POSTER_PATH = "poster_path";
        String ADULT = "adult";
        String OVERVIEW = "overview";
        String RELEASE_DATE = "release_date";
        String ID = "id";
        String ORIGINAL_TITLE = "original_title";
        String ORIGINAL_LANGUAGE = "original_language";
        String BACKDROP_PATH = "backdrop_path";
        String POPULARITY = "popularity";
        String VOTE_COUNT = "vote_count";
        String VIDEO = "video";
        String VOTE_AVERAGE = "vote_average";
        String MOVIES_STATE = "movie_state";
    }

}
