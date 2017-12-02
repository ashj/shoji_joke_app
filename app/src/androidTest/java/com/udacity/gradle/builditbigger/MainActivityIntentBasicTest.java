package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentBasicTest {
    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule =
            new IntentsTestRule(MainActivity.class);

    private IdlingResource mIdlingResource;

    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    @Before
    public void registerIdlingResource() {
        mIdlingResource = mIntentsTestRule.getActivity().getIdlingResource();
        //Espresso.registerIdlingResources(mIdlingResource);
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
    }

    /*
    Tests if MainActivity sends a joke in EXTRA_JOKE_TEXT
     */
    @Test
    public void testJokeButtonSendIntentWithExtra() {
        onView(withId(R.id.fragment_main_tell_joke_button))
                .check(matches(withText(R.string.button_text)));
        onView(withId(R.id.fragment_main_tell_joke_button))
                .perform(click());

        intended(allOf(
                hasExtraWithKey(com.shoji.example.android.androidjokes.ui.MainActivity.EXTRA_JOKE_TEXT)));
    }


    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            //Espresso.unregisterIdlingResources(mIdlingResource);
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
