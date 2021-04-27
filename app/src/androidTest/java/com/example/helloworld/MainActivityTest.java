package com.example.helloworld;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen() {
        onView(withId(R.id.name_and_date_id))
                .check(matches(withText(R.string.name_and_date_string)));
    }

    @Test
    public void validateEditText() {
        onView(withId(R.id.name))
                .perform(typeText("Kevin"))
                .check(matches(withText("Kevin")));
        onView(withId(R.id.email))
                .perform(typeText("k@gmail.com"))
                .check(matches(withText("k@gmail.com")));
        onView(withId(R.id.username))
                .perform(typeText("kevin.le"))
                .check(matches(withText("kevin.le")));
    }

    @Test
    public void validateDatePicker() {
        onView(withId(R.id.birth_date))
                .perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName())))
                .perform(PickerActions.setDate(1997, 11, 18));
    }

    @Test
    public void validateSignUp() {
        onView(withId(R.id.name))
                .perform(typeText("Kevin"));
        onView(withId(R.id.email))
                .perform(typeText("k@gmail.com"));
        onView(withId(R.id.username))
                .perform(typeText("kevin.le"));
        onView(withId(R.id.birth_date))
                .perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName())))
                .perform(PickerActions.setDate(1997, 11, 18));
        onView(withText("OK"))
                .perform(click());
        Intents.init();
        onView(withId(R.id.sign_up_button))
                .perform(scrollTo(), click());
        intended(hasComponent(SignUpActivity.class.getName()));
    }
}