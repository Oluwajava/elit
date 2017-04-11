package com.learn.udacity.elit.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.learn.udacity.elit.R;
import com.learn.udacity.elit.listeners.MovieListener;
import com.learn.udacity.elit.http.MovieTask;
import com.learn.udacity.elit.model.Movie;
import com.learn.udacity.elit.view.MovieView;

import java.util.List;

/**
 * Created by Mayokun on 4/10/2017.
 */

public class MoviePresenter implements MovieListener {

    private MovieView movieView;
    private List<Movie> movieList;
    private Context context;

    public MoviePresenter(Context context, MovieView movieView) {
        this.context = context;
        this.movieView = movieView;
    }

    public void makeServerCall(String url) {
        movieView.showProgressBar();
        movieView.hideErrorMessage();

        if(isNetworkAvailable()) {
            new MovieTask(this).execute(url);
        } else {
            movieView.hideProgressBar();
            movieView.showErrorMessage(R.string.network_not_available);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                                activeNetwork.isConnectedOrConnecting();
    }

    public List<Movie> getMovieList() {
        return this.movieList;
    }
    @Override
    public void onMovieClicked(int position) {
        movieView.gotoDetailActivity(movieList.get(position));
    }

    @Override
    public void onFinished(List<Movie> movieList) {
        this.movieList = movieList;
        movieView.hideProgressBar();
        movieView.populateMovieRecylerViewWithData(movieList, this);
    }

    @Override
    public void onFailed() {
        movieView.hideProgressBar();
        movieView.showErrorMessage(R.string.server_error);
    }
}
