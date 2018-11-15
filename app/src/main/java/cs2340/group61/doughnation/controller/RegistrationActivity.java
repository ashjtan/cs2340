package cs2340.group61.doughnation.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Pattern;

import cs2340.group61.doughnation.R;
import cs2340.group61.doughnation.model.domain.AccountType;
import cs2340.group61.doughnation.model.domain.accounts.Admin;
import cs2340.group61.doughnation.model.domain.accounts.LocationEmployee;
import cs2340.group61.doughnation.model.domain.accounts.OrganizationManager;
import cs2340.group61.doughnation.model.domain.accounts.User;
import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.Utils;

import static cs2340.group61.doughnation.model.Utils.isNotEmpty;

/**
 * Allows users to register a new account.
 */
@SuppressWarnings("ChainedMethodCall")
public class RegistrationActivity extends AppCompatActivity {

    private Spinner acctType;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText pw;
    private EditText confirmPw;

    private String firstText;
    private String lastText;
    private String emailText;
    private String pwText;
    private String confPwText; //populated w/ form values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Instantiating xml elements
        //Button toLogin = findViewById(R.id.return_login_Button);
        //Button register = findViewById(R.id.register_Button);
        acctType = findViewById(R.id.acctType_spinner);
        firstName = findViewById(R.id.firstName_editText);
        lastName = findViewById(R.id.lastName_editText);
        email = findViewById(R.id.email_editText);
        pw = findViewById(R.id.pickPassword_editText);
        confirmPw = findViewById(R.id.confirmPassword_editText);

        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        }
        assert actionbar != null;
        actionbar.setTitle("DoughNation");


        //Populates spinner w/ account types
        ArrayAdapter acctTypeAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, AccountType.values());
        acctTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acctType.setAdapter(acctTypeAdapter);
    }


    /**
     * Allows the user to switch back to the login page.
     * @param v The page.
     */
    //EVENT HANDLERS
    public void switchToLogin(View v) {
        //switches to login page
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    /**
     * Allows users to create a new account.
     * @param v The page.
     */
    @SuppressWarnings("FeatureEnvy")
    public void register(View v) {
        Utils.clearTextValues(firstText, lastText, emailText, pwText, confPwText);
        loadFormValues();

        if (validForm()) {
            createUser();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    new ContextThemeWrapper(this, R.style.AppTheme));
            alertDialog.setTitle("Account Created");
            alertDialog.setMessage("Success!");
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent myIntent = new Intent(((Dialog) dialog).getContext(),
                            LoginActivity.class);
                    startActivity(myIntent);
                }
            });
            alertDialog.show();
        }
        else {
            Utils.showDialog("Account not created." +
                            " Please check all forms before submitting.",
                    "Unable to Create Account", this);
        }
    }


    //HELPER METHODS

    private boolean validForm() {
        return allFieldsComplete() && validName() && validEmail() && validPassword();
    }

    private boolean allFieldsComplete(){
        return isNotEmpty(firstText, lastText, emailText, pwText, confPwText);
//        && !acctType.getSelectedItem().equals(null);
    }

    private boolean validName() {
        return firstText.matches("[a-zA-Z]+") && lastText.matches("[a-zA-Z]+");
    }

    private boolean validEmail() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailText == null) {
            return false;
        }
        return pat.matcher(emailText).matches();
    }

    private boolean validPassword() {
        return confPwText.equals(pwText);
    }

    @SuppressWarnings("FeatureEnvy")
    private void createUser() {
        AccountType acctSelect = (AccountType) acctType.getSelectedItem();
        String name = firstText.trim() + " " + lastText.trim();

        switch(acctSelect) {
            case ADMIN:
                AppData.addAccount(new Admin(name, emailText.trim(), pwText));
                break;
            case GENERAL_USER:
                AppData.addAccount(new User(name, emailText.trim(), pwText));
                break;
            case LOCATION_EMPLOYEE:
                AppData.addAccount(new LocationEmployee(name, emailText.trim(), pwText));
                break;
            case ORGANIZATION_MANAGER:
                AppData.addAccount(new OrganizationManager(name, emailText.trim(), pwText));
                break;
                default:
                    showCreateAccountError();
                    break;
        }
    }

    private void showCreateAccountError() {
        Utils.showDialog("One of the fields is invalid."
                + " Please check form before submitting.",
                "Unable to Create Account", this);
    }

    private void loadFormValues() {
        firstText = firstName.getText().toString();
        lastText = lastName.getText().toString();
        emailText = email.getText().toString();
        pwText = pw.getText().toString();
        confPwText = confirmPw.getText().toString();
    }
}