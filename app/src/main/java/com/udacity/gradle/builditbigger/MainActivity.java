package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.endpoint.EndpointsAsyncTask;


public class MainActivity extends AppCompatActivity
    implements EndpointsAsyncTask.OnFetchJokeFinishedListener {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.fragment_main_progressbar);
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickTellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        Context context = this;
        EndpointsAsyncTask.OnFetchJokeFinishedListener handler = this;
        new EndpointsAsyncTask(context, handler).execute();
    }


    @Override
    public void onFetchJokeFinished(String result) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        Context context = this;
        Intent intent = new Intent(context,
                com.shoji.example.android.androidjokes.ui.MainActivity.class);
        intent.putExtra(com.shoji.example.android.androidjokes.ui.MainActivity.EXTRA_JOKE_TEXT, result);
        startActivity(intent);
    }
}
