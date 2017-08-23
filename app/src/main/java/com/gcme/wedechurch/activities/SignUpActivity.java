package com.gcme.wedechurch.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gcme.wedechurch.MainActivity;
import com.gcme.wedechurch.PrefManagers.UserPrefManager;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.Services.APIResponseService;
import com.gcme.wedechurch.Services.RequestCallback;
import com.gcme.wedechurch.Services.RequestServices;
import com.gcme.wedechurch.Services.SendRequestService;
import com.gcme.wedechurch.Services.signUpProcessResult;
import com.gcme.wedechurch.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import kotlin.Pair;

/**
 * Created by bengeos on 7/24/17.
 */

public class SignUpActivity extends AppCompatActivity implements RequestCallback, signUpProcessResult {

    private static final String TAG = "SignupActivity";

    private Context myContext;
    private TextView btnLogin;
    private APIResponseService apiResponseService;
    private SendRequestService mySendRequestService;
    private List<Pair<String,String>> SendParam;
    private ProgressDialog myProgressDialog;
    private UserPrefManager userPrefManager;
    private User New_User;
    private Gson myParser;

 EditText _nameFText;
 EditText _nameLText;
 EditText _userNameText;
 EditText _countryText;
 EditText _emailText;
 EditText _mobileText;
 EditText _passwordText;
 EditText _reEnterPasswordText;
    AppCompatButton btnSignUp;
    TextView loginLink;

    Spinner spinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        myContext = this;
        myProgressDialog = new ProgressDialog(myContext);
        apiResponseService = new APIResponseService();
        userPrefManager = new UserPrefManager(myContext);
        loginLink=(TextView) findViewById(R.id.link_login);

        _nameFText=(EditText)findViewById (R.id.input_first_name);
        _nameLText=(EditText)findViewById(R.id.input_last_name);
        _userNameText=(EditText)findViewById(R.id.input_user_name) ;
                _countryText=(EditText)findViewById(R.id.input_country);
                _emailText=(EditText)findViewById(R.id.input_email);
        _mobileText=(EditText)findViewById(R.id.input_mobile);
        _passwordText=(EditText)findViewById(R.id.input_password);
        _reEnterPasswordText=(EditText)findViewById(R.id.input_reEnterPassword);

        myParser = new Gson();

        btnSignUp=(AppCompatButton) findViewById(R.id.btn_signup);
        spinner=  (Spinner) findViewById(R.id.spinner);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);

            }
        });

        addItemsOnSpinner();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });



       }

    public void addItemsOnSpinner() {
        List<String> list = new ArrayList<String>();
        list.add("M");
        list.add("F");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            Toast.makeText(myContext,"Invalid information provided!!!",Toast.LENGTH_LONG).show();
            return;
        }else {
            sendRegisterRequest();
        }




    }


    public boolean validate() {
        boolean valid = true;

        String fname = _nameFText.getText().toString();
        String lname = _nameLText.getText().toString();
        String uname = _userNameText.getText().toString();
        String address = _countryText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (fname.isEmpty() || fname.length() < 3) {
            _nameFText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameFText.setError(null);
        }

        if (lname.isEmpty() || lname.length() < 3) {
            _nameLText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameLText.setError(null);
        }
        if (uname.isEmpty() || uname.length() < 5) {
            _userNameText.setError("at least 5 characters");
            valid = false;
        } else {
            _userNameText.setError(null);
        }

        if (address.isEmpty()) {
            _countryText.setError("Enter Valid Address");
            valid = false;
        } else {
            _countryText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }



    public void sendRegisterRequest(){
            myProgressDialog.setTitle("Registering ....");
            myProgressDialog.show();




        New_User = new User();
        New_User.setFirst_name(_nameFText.getText().toString());
        New_User.setLast_name(_nameLText.getText().toString());
        New_User.setEmail(_emailText.getText().toString());
        New_User.setPhone_number(_mobileText.getText().toString());
        New_User.setSex(String.valueOf(spinner.getSelectedItem()));
        New_User.setCountry(_countryText.getText().toString());
        New_User.setUser_pass(_passwordText.getText().toString());
        New_User.setUser_name(_userNameText.getText().toString());


        ArrayList<User> user = new ArrayList<User>();
        user.add(New_User);


        SendParam = new ArrayList<>();
            SendParam.add(new Pair<String, String>(RequestServices.REGISTRATION_REQUEST.USER_NAME.toString(),""));
            SendParam.add(new Pair<String, String>(RequestServices.REGISTRATION_REQUEST.PASSWORD.toString(),""));
            SendParam.add(new Pair<String, String>(RequestServices.MainServices.toString(),RequestServices.REGISTRATION_REQUEST.SERVICE.toString()));
            SendParam.add(new Pair<String, String>(RequestServices.PARAM.toString(), myParser.toJson(user)));
            mySendRequestService = new SendRequestService(SendParam, RequestServices.MainAPI);
            mySendRequestService.processRequest(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void successCallback(String str) {
        myProgressDialog.cancel();
        apiResponseService.processSignupResponse(str,this);
        Toast.makeText(myContext,"Welcome to Wedechurch!!!",Toast.LENGTH_LONG).show();

    }

    @Override
    public void failedCallback(String string) {
        myProgressDialog.cancel();
    }

    @Override
    public void onNormalUserSignup() {
        Toast.makeText(myContext,"This is Normal User",Toast.LENGTH_SHORT).show();
        userPrefManager.setUserTypeAsNormalUser(true);
        getMainActivity();
    }

    private void getMainActivity() {
        Intent intent = new Intent(myContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myContext.startActivity(intent);
        finish();
    }

    @Override
    public void onAgentUserSignup() {
        Toast.makeText(myContext,"This is Normal User",Toast.LENGTH_SHORT).show();
        userPrefManager.setUserTypeAsNormalUser(false);
        getMainActivity();
    }

    @Override
    public void onProcessError(String error_string) {
        Toast.makeText(myContext,"Something went wrong try again",Toast.LENGTH_SHORT).show();
    }
}
