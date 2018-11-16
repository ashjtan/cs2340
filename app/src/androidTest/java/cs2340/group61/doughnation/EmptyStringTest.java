package cs2340.group61.doughnation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static cs2340.group61.doughnation.model.Utils.isNotEmpty;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class EmptyStringTest {
    @Test
    public void useIsNotEmpty() {
        String[] stringArray = new String[5];
        stringArray[0] = "clothes";
        stringArray[1] = "hats";
        stringArray[2] = "shoes";
        stringArray[3] = "bags";
        stringArray[4] = "books";

        String[] emptyArray = new String[2];
        String emptyString = new String();
        String oneString = new String("computer");
        String secondString = new String("food");
        String thirdString = new String("money");
        String spaceString = new String("   ");
        String spaceAroundString = new String("  name  ");

        boolean testStringArray = isNotEmpty(stringArray);
        boolean testEmptyArray = isNotEmpty(emptyArray);
        boolean testEmptyString = isNotEmpty(emptyString);
        boolean testOneString = isNotEmpty(oneString);
        boolean testMultipleParams = isNotEmpty(oneString, secondString, thirdString);
        boolean testSpaceString = isNotEmpty(spaceString);
        boolean testSpaceAroundString = isNotEmpty(spaceAroundString);

        assertEquals(true, testStringArray);
        assertEquals(false, testEmptyArray);
        assertEquals(false, testEmptyString);
        assertEquals(true, testOneString);
        assertEquals(true, testMultipleParams);
        assertEquals(false, testSpaceString);
        assertEquals(true, testSpaceAroundString);
    }
}
