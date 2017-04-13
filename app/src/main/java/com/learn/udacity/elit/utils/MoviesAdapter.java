package com.learn.udacity.elit.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.learn.udacity.elit.R;
import com.learn.udacity.elit.listeners.MovieListener;
import com.learn.udacity.elit.model.Movie;

import java.util.List;

/**
 * Created by Mayokun on 4/10/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    private List<Movie> movieList;
    private Context context;
    private MovieListener movieListener;

    public MoviesAdapter(Context context, List<Movie> movieList, MovieListener movieListener) {
        this.context = context;
        this.movieList = movieList;
        this.movieListener = movieListener;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);

        MoviesViewHolder moviesViewHolder = new MoviesViewHolder(view);

        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Glide.with(context)
             .load(Constants.Endpoints.IMAGE_BASE_URL+movieList.get(position).getPosterPath())
             .placeholder(android.R.drawable.picture_frame)
             .error(android.R.drawable.picture_frame)
             .into(holder.movieThumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }



    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView movieThumbnail;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.movie_thumbnail);

            movieThumbnail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            movieListener.onMovieClicked(getAdapterPosition());
        }
    }
}
