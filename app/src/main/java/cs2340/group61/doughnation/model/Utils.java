package cs2340.group61.doughnation.model;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Has basic methods used throughout the app.
 */
@SuppressWarnings("ChainedMethodCall")
public class Utils {

    /**
     * Checks that strings are not null/empty.
     * Primarily for checking that EditText form fields are filled out.
     * @param strings Strings to check.
     * @return a boolean -- true if the field is empty; false otherwise.
     */
    public static boolean isNotEmpty(String... strings) {
        for (String s : strings) {
            if ((s == null) || s.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets string values to empty.
     * Primarily for setting String vars from EditText back to blank for rechecking values.
     * @param strings Strings to set to empty.
     */
    public static void clearTextValues(String... strings) {
        for (String s : strings) {
            s = "";
        }
    }

    /**
     * Shows custom error dialog.
     * @param message The message to display.
     * @param title The name of the dialog box.
     * @param activity Should always be called with "this" as param.
     */
    @SuppressWarnings("TypeMayBeWeakened")
    public static void showDialog(String message, String title, AppCompatActivity activity) {
        AlertDialog.Builder alert  = new AlertDialog.Builder(activity);
        alert.setMessage(message);
        alert.setTitle(title);
        alert.setPositiveButton("OK", null);

        alert.create().show();
    }
}
