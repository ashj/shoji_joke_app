package com.shoji.example.android.androidjokes.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.shoji.example.android.androidjokes.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE_TEXT = "com.shoji.example.android.androidjokes.extra.joke_text";


    private TextView mJokeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity_main);

        mJokeTextView = findViewById(R.id.activity_main_textview_joke);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(EXTRA_JOKE_TEXT)) {
            String joke = intent.getStringExtra(EXTRA_JOKE_TEXT);
            mJokeTextView.setText(joke);
        }
        else {
            mJokeTextView.setText(getString(R.string.no_intent_joke));
        }

    }
}
