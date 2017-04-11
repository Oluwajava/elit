package com.learn.udacity.elit.presenter;

import android.os.Parcelable;

import com.learn.udacity.elit.R;
import com.learn.udacity.elit.model.Movie;
import com.learn.udacity.elit.utils.Constants;
import com.learn.udacity.elit.view.DetailView;

/**
 * Created by Mayokun on 4/10/2017.
 */

public class DetailPresenter {

    private DetailView detailView;

    public DetailPresenter(DetailView detailView) {
        this.detailView = detailView;
    }


    public void populateViewWithData(Movie movie) {

        detailView.setImageThumbnail(Constants.Endpoints.IMAGE_BASE_URL+movie.getPosterPath());
        detailView.setName(movie.getOriginalTitle());
        detailView.setRating(""+movie.getVoteAverage());
        detailView.setReleaseDate(""+movie.getReleaseDate());
        detailView.setSynopsis(movie.getOverview());

    }
}
