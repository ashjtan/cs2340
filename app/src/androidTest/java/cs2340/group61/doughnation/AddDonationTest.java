package cs2340.group61.doughnation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cs2340.group61.doughnation.controller.AddDonationActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
public class AddDonationTest {

    @Rule
    public ActivityTestRule<AddDonationActivity> activityTestRule
            = new ActivityTestRule<>(AddDonationActivity.class);

    // Testing button with no text entered
    @Test
    public void testButtonEmptyText() {
        // Press register button
        onView(withId(R.id.done_button))
                .perform(click());
        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));
    }


    //Testing Button with only name entered
    @Test
    public void testNameOnly() {
        // Type into Views
        onView(withId(R.id.add_name))
                .perform(typeText("Valid Donation Name"), closeSoftKeyboard());

        // Press add donation button
        onView(withId(R.id.done_button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));

    }

    //Testing Button with only value entered
    @Test
    public void testValueOnly() {
        // Type into Views
        onView(withId(R.id.add_value))
                .perform(typeText("4.20"), closeSoftKeyboard());

        // Press add donation button
        onView(withId(R.id.done_button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));

    }

    //Testing Button with only description entered
    @Test
    public void testDescriptionOnly() {
        // Type into Views
        onView(withId(R.id.add_description))
                .perform(typeText("This is a valid description of the donation"), closeSoftKeyboard());

        // Press add donation button
        onView(withId(R.id.done_button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));

    }


//    //Testing value with letters
//    @Test
//    public void testLettersValue() {
//        // Type into Views
//        onView(withId(R.id.add_value))
//                .perform(typeText("1s23.05"), closeSoftKeyboard());
//        onView(withId(R.id.add_name))
//                .perform(typeText("Valid donation name"), closeSoftKeyboard());
//        onView(withId(R.id.add_description))
//                .perform(typeText("This is a valid donation description"), closeSoftKeyboard());
//
//
//        // Press add donation button
//        onView(withId(R.id.done_button))
//                .perform(click());
//
//        // Listen for success confirmation
//        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));
//
//    }
//
//    //Testing value entry with special characters other than '.'
//    @Test
//    public void testCharactersValue() {
//        // Type into Views
//        onView(withId(R.id.add_value))
//                .perform(typeText("1;23.05"), closeSoftKeyboard());
//        onView(withId(R.id.add_name))
//                .perform(typeText("Valid donation name"), closeSoftKeyboard());
//        onView(withId(R.id.add_description))
//                .perform(typeText("This is a valid donation description"), closeSoftKeyboard());
//
//
//        // Press add donation button
//        onView(withId(R.id.done_button))
//                .perform(click());
//
//        // Listen for success confirmation
//        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));
//
//    }

    // Testing value entry with too many decimal places
    @Test
    public void testDecimalValue() {
        // Type into Views
        onView(withId(R.id.add_value))
                .perform(typeText("1234.567"), closeSoftKeyboard());
        onView(withId(R.id.add_name))
                .perform(typeText("Valid donation name"), closeSoftKeyboard());
        onView(withId(R.id.add_description))
                .perform(typeText("This is a valid donation description"), closeSoftKeyboard());



        // Press add donation button
        onView(withId(R.id.done_button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));

    }

    // Testing value entry with no decimal places or not enough
    @Test
    public void testNoDecimalValue() {
        // Type into Views
        onView(withId(R.id.add_value))
                .perform(typeText("1234.1"), closeSoftKeyboard());
        onView(withId(R.id.add_name))
                .perform(typeText("Valid donation name"), closeSoftKeyboard());
        onView(withId(R.id.add_description))
                .perform(typeText("This is a valid donation description"), closeSoftKeyboard());



        // Press add donation button
        onView(withId(R.id.done_button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));

    }

    // Testing value entry with no decimal
    @Test
    public void testNoDecimal() {
        // Type into Views
        onView(withId(R.id.add_value))
                .perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.add_name))
                .perform(typeText("Valid donation name"), closeSoftKeyboard());
        onView(withId(R.id.add_description))
                .perform(typeText("This is a valid donation description"), closeSoftKeyboard());



        // Press add donation button
        onView(withId(R.id.done_button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Empty or invalid inputs somewhere.")).check(matches(isDisplayed()));
    }

    // Testing value entry with no decimal
    @Test
    public void testValidText() {
        // Type into Views
        onView(withId(R.id.add_value))
                .perform(typeText("1234.56"), closeSoftKeyboard());
        onView(withId(R.id.add_name))
                .perform(typeText("Valid donation name"), closeSoftKeyboard());
        onView(withId(R.id.add_description))
                .perform(typeText("This is a valid donation description"), closeSoftKeyboard());


        // Press add donation button
        onView(withId(R.id.done_button))
                .perform(click());

        // Listen for success confirmation
        onView(withText("Success! Donation Added!")).check(matches(isDisplayed()));
    }

}
