package cs2340.group61.doughnation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static cs2340.group61.doughnation.model.Utils.isNotEmpty;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class TestingMethod {
    @Test
    public void useMethod() {
        boolean works = isNotEmpty("jasmine");
        assertEquals(true, works);
    }
}
