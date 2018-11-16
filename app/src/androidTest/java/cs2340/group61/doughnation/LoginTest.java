package cs2340.group61.doughnation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import cs2340.group61.doughnation.controller.LoginActivity;
import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.domain.accounts.User;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Ashley Tan
 */
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule
            = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void createTestUser() {
        AppData.addAccount(new User(
                "test", "test@gmail.com", "testpw"));
    }

    @Test
    public void testValidLogin() {
        onView(withId(R.id.email))
                .perform(typeText("test@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText("testpw"), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withText("Logout")).check(matches(isDisplayed()));
    }

    @Test
    public void testNonUser() {
        onView(withId(R.id.email))
                .perform(typeText("incorrect@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText("testpw"), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withText("Invalid Login")).check(matches(isDisplayed()));

    }

    @Test
    public void testNoEmail() {
        onView(withId(R.id.password))
                .perform(typeText("testpw"), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withText("Invalid Login")).check(matches(isDisplayed()));
    }

    @Test
    public void testNoPass() {
        onView(withId(R.id.email))
                .perform(typeText("test@gmail.com"), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withText("Invalid Login")).check(matches(isDisplayed()));
    }

    @Test
    public void testIncorrectPass() {
        onView(withId(R.id.email))
                .perform(typeText("test@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText("incorrect"), closeSoftKeyboard());

        onView(withId(R.id.login_button))
                .perform(click());

        onView(withText("Invalid Login")).check(matches(isDisplayed()));
    }

}

