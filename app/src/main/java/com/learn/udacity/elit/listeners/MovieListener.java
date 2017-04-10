package com.learn.udacity.elit.listeners;

import com.learn.udacity.elit.model.Movie;

import java.util.List;

/**
 * Created by Mayokun on 4/9/2017.
 */

public interface MovieListener {
    void onMovieClicked(int position);
    void onFinished(List<Movie> movieList);
    void onFailed();
}
