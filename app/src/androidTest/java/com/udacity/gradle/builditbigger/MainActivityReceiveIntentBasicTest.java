package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityReceiveIntentBasicTest {
    @Rule
    public ActivityTestRule<com.shoji.example.android.androidjokes.ui.MainActivity> mActivityTestRule =
            new ActivityTestRule(com.shoji.example.android.androidjokes.ui.MainActivity.class);

    /*
    Tests if com.shoji.example.android.androidjokes.ui.MainActivity receives the joke in EXTRA_JOKE_TEXT
     */
    @Test
    public void testJokeButtonReceiveIntentWithExtra() {
        String jokeTest = "This is a test, not a joke";

        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext,
                com.shoji.example.android.androidjokes.ui.MainActivity.class);
        intent.putExtra(com.shoji.example.android.androidjokes.ui.MainActivity.EXTRA_JOKE_TEXT, jokeTest);
        mActivityTestRule.launchActivity(intent);

        onView(withId(R.id.activity_main_textview_joke))
                .check(matches(withText(jokeTest)));

    }


}
