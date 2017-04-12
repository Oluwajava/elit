package com.learn.udacity.elit;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.udacity.elit.listeners.MovieListener;
import com.learn.udacity.elit.presenter.MoviePresenter;
import com.learn.udacity.elit.utils.Constants;
import com.learn.udacity.elit.model.Movie;
import com.learn.udacity.elit.utils.MoviesAdapter;
import com.learn.udacity.elit.view.MovieView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieView{
    private ProgressBar progressBar;
    private RecyclerView movieRecyclerView;
    private TextView errorMessageTextView;

    private MoviesAdapter moviesAdapter;
    private GridLayoutManager layoutManager;
    private MoviePresenter moviePresenter;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<Movie> movies = moviePresenter.getMovieList();
        outState.putParcelableArrayList(Constants.Keys.MOVIES_STATE, (ArrayList<? extends Parcelable>) movies);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviePresenter = new MoviePresenter(this, this);
        initializeWidgets();

        if(savedInstanceState != null) {
            if(savedInstanceState.getParcelableArrayList(Constants.Keys.MOVIES_STATE) != null) {
                moviePresenter.onFinished(savedInstanceState.<Movie>getParcelableArrayList(Constants.Keys.MOVIES_STATE));
            }
        } else {
            moviePresenter.makeServerCall(Constants.Endpoints.POPULAR_URL);
        }

    }

    public void initializeWidgets() {
        movieRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
        layoutManager = new GridLayoutManager(this, numberOfColumns());
        movieRecyclerView.setLayoutManager(layoutManager);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        errorMessageTextView = (TextView) findViewById(R.id.error_message);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // You can change this divider to adjust the size of the poster
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    @Override
    public void populateMovieRecylerViewWithData(List<Movie> movieList, MovieListener movieListener) {
        moviesAdapter = new MoviesAdapter(this, movieList, movieListener);
        movieRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(int stringId) {
        movieRecyclerView.setVisibility(View.INVISIBLE);
        errorMessageTextView.setText(stringId);
        errorMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        movieRecyclerView.setVisibility(View.VISIBLE);
        errorMessageTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gotoDetailActivity(Movie movie) {
        Intent detailActivityIntent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.Keys.MOVIES_STATE, movie);
        detailActivityIntent.putExtras(bundle);
        startActivity(detailActivityIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_popularity:
                moviePresenter.makeServerCall(Constants.Endpoints.POPULAR_URL);
                break;
            case R.id.sort_by_rating:
                moviePresenter.makeServerCall(Constants.Endpoints.TOP_RATED_URL);
                break;
        }
        return true;
    }
}
