package com.learn.udacity.elit.view;

/**
 * Created by Mayokun on 4/10/2017.
 */

public interface DetailView {
    void setImageThumbnail(String url);
    void setName(String name);
    void setReleaseDate(String releaseDate);
    void setRating(String rating);
    void setSynopsis(String synopsis);
}
