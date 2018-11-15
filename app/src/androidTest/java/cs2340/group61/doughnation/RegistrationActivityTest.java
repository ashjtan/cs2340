package cs2340.group61.doughnation;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cs2340.group61.doughnation.controller.LoginActivity;
import cs2340.group61.doughnation.controller.RegistrationActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RegistrationActivityTest {

    @Rule
    public ActivityTestRule<RegistrationActivity> activityTestRule
            = new ActivityTestRule<>(RegistrationActivity.class);

    // Testing button with no text entered
    @Test
    public void testButtonEmptyText() {
        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));
    }

    //Testing Button with only First Name entered
    @Test
    public void testFirstOnly() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("John"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing Button with only Last Name entered
    @Test
    public void testLastOnly() {
        // Type into Views
        onView(withId(R.id.lastName_editText))
                .perform(typeText("Doe"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing Button with only email entered
    @Test
    public void testEmailOnly() {
        // Type into Views
        onView(withId(R.id.email_editText))
                .perform(typeText("JohnDoe@gmail.com"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing Button with only password entered
    @Test
    public void testPasswordOnly() {
        // Type into Views
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing mismatching passwords
    @Test
    public void testMismatchedPasswords() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastName_editText))
                .perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.email_editText))
                .perform(typeText("JohnDoe@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish2"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing mismatching passwords with different letter cases
    @Test
    public void testBarelyMismatchedPasswords() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastName_editText))
                .perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.email_editText))
                .perform(typeText("JohnDoe@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gIbberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing a gibberish email
    @Test
    public void testNonsenseEmail() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastName_editText))
                .perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.email_editText))
                .perform(typeText(".comJohnDoe@@.."), closeSoftKeyboard());
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing an email with only letters (no @ or .)
    @Test
    public void testNonsenseEmailOnlyLetters() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastName_editText))
                .perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.email_editText))
                .perform(typeText("JohnDoesFakeEmail"), closeSoftKeyboard());
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing First name entry with numbers
    @Test
    public void testNonsenseFirstName() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("J0hn"), closeSoftKeyboard());
        onView(withId(R.id.lastName_editText))
                .perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.email_editText))
                .perform(typeText("JohnDoesFakeEmail"), closeSoftKeyboard());
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    //Testing Last name entry with numbers
    @Test
    public void testNonsenseLastName() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastName_editText))
                .perform(typeText("D0e"), closeSoftKeyboard());
        onView(withId(R.id.email_editText))
                .perform(typeText("JohnDoesFakeEmail"), closeSoftKeyboard());
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Account not created." +
                " Please check all forms before submitting.")).check(matches(isDisplayed()));

    }

    // Testing entering valid fields into EditTexts on Page
    @Test
    public void testTypeValidText() {
        // Type into Views
        onView(withId(R.id.firstName_editText))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.lastName_editText))
                .perform(typeText("Doe"), closeSoftKeyboard());
        onView(withId(R.id.email_editText))
                .perform(typeText("JohnDoe@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.pickPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword_editText))
                .perform(typeText("gibberish1"), closeSoftKeyboard());

        // Press register button
        onView(withId(R.id.register_Button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Success!")).check(matches(isDisplayed()));

    }

}
