package com.learn.udacity.elit.view;

import com.learn.udacity.elit.listeners.MovieListener;
import com.learn.udacity.elit.model.Movie;

import java.util.List;

/**
 * Created by Mayokun on 4/8/2017.
 */

public interface MovieView {
    void populateMovieRecylerViewWithData(List<Movie> movieList, MovieListener movieListener);
    void showProgressBar();
    void hideProgressBar();
    void showErrorMessage(int stringId);
    void hideErrorMessage();
    void gotoDetailActivity(Movie movie);
}
