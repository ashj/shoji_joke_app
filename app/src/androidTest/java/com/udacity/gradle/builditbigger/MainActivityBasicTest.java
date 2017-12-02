package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityBasicTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule(MainActivity.class);

    private IdlingResource mIdlingResource;

    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        //Espresso.registerIdlingResources(mIdlingResource);
        IdlingRegistry.getInstance().register(mIdlingResource);
    }


    @Test
    public void testGetJokeButton() {
        String label = "TELL JOKE";
        // Main activity, click on first
        onView(withId(R.id.fragment_main_tell_joke_button))
                .check(matches(withText(R.string.button_text)));
        onView(withId(R.id.fragment_main_tell_joke_button))
                .perform(click());

//        onView(withId(R.id.activity_recipe_master_list))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(ingredientPosition, click()));
//
//        onView(withId(R.id.activity_recipe_ingredients))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(ingredientListPosition, click()));
//               //check(matches(withText(text))));
//
//        onView(withId(R.id.activity_recipe_ingredients))
//                .perform(scrollToPosition(ingredientListPosition))
//                .check(matches(atPosition(ingredientListPosition, withText(text))));



    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            //Espresso.unregisterIdlingResources(mIdlingResource);
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
