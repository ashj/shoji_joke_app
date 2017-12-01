package com.udacity.gradle.builditbigger.endpoint;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
// Do not remove import bellow.
import com.udacity.gradle.builditbigger.backend.jokerApi.JokerApi;

import java.io.IOException;



public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static JokerApi myApiService = null;
    private Context mContext;
    private OnFetchJokeFinishedListener mOnFetchJokeHandler;

    public interface OnFetchJokeFinishedListener {
        void onFetchJokeFinished(String result);
    }

    public EndpointsAsyncTask(Context context,
                              OnFetchJokeFinishedListener onFetchJokeFinishedHandler) {
        mContext = context;
        mOnFetchJokeHandler = onFetchJokeFinishedHandler;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {  // Only do this once

            JokerApi.Builder builder = new JokerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        mOnFetchJokeHandler.onFetchJokeFinished(result);

    }
}
