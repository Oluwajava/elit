package com.learn.udacity.elit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.learn.udacity.elit.model.Movie;
import com.learn.udacity.elit.presenter.DetailPresenter;
import com.learn.udacity.elit.utils.Constants;
import com.learn.udacity.elit.view.DetailView;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private ImageView movieThumbnail;
    private TextView nameTextView;
    private TextView releaseDateTextView;
    private TextView ratingTextView;
    private TextView synopsisTextView;

    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeWidgets();

        detailPresenter = new DetailPresenter(this);

        Bundle bundle = getIntent().getExtras();
        Movie movie = bundle.getParcelable(Constants.Keys.MOVIES_STATE);
        detailPresenter.populateViewWithData(movie);

    }

    private void initializeWidgets() {
        movieThumbnail = (ImageView) findViewById(R.id.thumbnail);
        nameTextView = (TextView) findViewById(R.id.tv_title);
        releaseDateTextView = (TextView) findViewById(R.id.tv_realease_date);
        ratingTextView = (TextView) findViewById(R.id.tv_rating);
        synopsisTextView = (TextView) findViewById(R.id.tv_synopsis);
    }

    @Override
    public void setImageThumbnail(String url) {
        Glide.with(this).load(url).into(movieThumbnail);
    }

    @Override
    public void setName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    @Override
    public void setRating(String rating) {
        ratingTextView.setText(rating);
    }

    @Override
    public void setSynopsis(String synopsis) {
        synopsisTextView.setText(synopsis);
    }
}
