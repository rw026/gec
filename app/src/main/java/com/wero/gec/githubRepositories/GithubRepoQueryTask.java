package com.wero.gec.githubRepositories;

import android.os.AsyncTask;
import android.util.Log;

import com.wero.gec.Utils.NetworkUtil;

import java.io.IOException;
import java.net.URL;

public class GithubRepoQueryTask extends AsyncTask<URL, Void, String> {
    @Override
    protected String doInBackground(URL... urls) {
        URL searchUrl = urls[0];
        String jsonResult = null;
        try {
            jsonResult = NetworkUtil.getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
            Log.e(getClass().getName(), "Error getting response from github");
        }

        return jsonResult;
    }
}
