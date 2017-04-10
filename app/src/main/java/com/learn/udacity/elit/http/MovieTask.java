package com.learn.udacity.elit.http;

import android.net.Uri;
import android.os.AsyncTask;

import com.learn.udacity.elit.listeners.MovieListener;
import com.learn.udacity.elit.utils.Constants;
import com.learn.udacity.elit.utils.JSONUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mayokun on 4/8/2017.
 */

public class MovieTask extends AsyncTask<String, Void, String> {

    MovieListener movieListener;

    public MovieTask(MovieListener movieListener) {
        this.movieListener = movieListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String passedUrl = strings[0];
        String moviesResult = null;

        Uri uri = Uri.parse(passedUrl).buildUpon()
                .appendQueryParameter(Constants.Keys.API_KEY, Constants.Keys.MY_API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
            moviesResult = NetworkUtils.makeNetworkCall(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesResult;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s != null) {
            movieListener.onFinished(JSONUtils.getJsonFromString(s));
        } else {
            movieListener.onFailed();
        }


    }
}

