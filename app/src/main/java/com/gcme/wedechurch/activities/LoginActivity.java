package com.gcme.wedechurch.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.gcme.wedechurch.MainActivity;
import com.gcme.wedechurch.PrefManagers.UserPrefManager;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.Services.APIResponseService;
import com.gcme.wedechurch.Services.RequestCallback;
import com.gcme.wedechurch.Services.RequestServices;
import com.gcme.wedechurch.Services.SendRequestService;
import com.gcme.wedechurch.Services.loginProcessResult;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import kotlin.Pair;

/**
 * Created by bengeos on 7/24/17.
 */

public class LoginActivity extends AppCompatActivity implements RequestCallback, loginProcessResult {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private AppCompatButton btnLogin;
    private Context myContext;
    private TextView btnSignup, btnSkipLogin;
    private EditText txtEmail,txtPass;
    private ProgressDialog myProgressDialog;
    private SendRequestService mySendRequestService;
    private List<Pair<String,String>> SendParam;
    private APIResponseService apiResponseService;
    private UserPrefManager userPrefManager;
    TextView mForgotPassword;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myContext = this;
        myProgressDialog = new ProgressDialog(myContext);
        apiResponseService = new APIResponseService();
        userPrefManager = new UserPrefManager(myContext);

        txtEmail = (EditText) findViewById(R.id.input_email);
        txtPass = (EditText) findViewById(R.id.input_password);
        mForgotPassword=(TextView) findViewById (R.id.forgotPassword);
        btnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myProgressDialog.setTitle("Authenticating ....");
                myProgressDialog.show();
                sendLoginRequest();
            }
        });
        btnSignup = (TextView) findViewById(R.id.link_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);
            }
        });
        btnSkipLogin = (TextView) findViewById(R.id.link_skip);
        btnSkipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);
                finish();
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getApplicationContext(), RecoveryActivity.class);
                startActivity(i);

                finish();


            }
        });

    }

    private void sendLoginRequest() {
        if(txtEmail.getText().toString().length()>3 && txtPass.getText().toString().length()>5){
            SendParam = new ArrayList<>();
            SendParam.add(new Pair<String, String>(RequestServices.AUTHENTICATION.USER_EMAIL.toString(),txtEmail.getText().toString()));
            SendParam.add(new Pair<String, String>(RequestServices.AUTHENTICATION.USER_PASS.toString(),txtPass.getText().toString()));
            SendParam.add(new Pair<String, String>(RequestServices.MainServices.toString(),RequestServices.AUTHENTICATION.SERVICE.toString()));
            SendParam.add(new Pair<String, String>(RequestServices.PARAM.toString(),txtPass.getText().toString()));

            mySendRequestService = new SendRequestService(SendParam, RequestServices.MainAPI);
            mySendRequestService.processRequest(this);
        }else {
            myProgressDialog.cancel();
            Toast.makeText(myContext,"Invalid phone number and password used",Toast.LENGTH_SHORT).show();
            txtPass.setText("");
            txtEmail.setText("");
        }
    }
    public void getMainActivity(){
        Intent intent = new Intent(myContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myContext.startActivity(intent);
        finish();
    }
    @Override
    public void successCallback(String str) {
        Toast.makeText(myContext,"Success response\n"+ str,Toast.LENGTH_SHORT).show();
        myProgressDialog.cancel();
        apiResponseService.processLoginResponse(str,this);
    }

    @Override
    public void failedCallback(String string) {
        Toast.makeText(myContext,string,Toast.LENGTH_SHORT).show();
        myProgressDialog.cancel();
    }

    @Override
    public void onNormalUserLogin() {
        Toast.makeText(myContext,"This is Normal User",Toast.LENGTH_SHORT).show();
        userPrefManager.setUserTypeAsNormalUser(true);
        getMainActivity();
    }

    @Override
    public void onAgnetUserLogin() {
        Toast.makeText(myContext,"This is Agent User",Toast.LENGTH_SHORT).show();
        userPrefManager.setUserTypeAsNormalUser(false);
        getMainActivity();
    }

    @Override
    public void onAuthorizedUserLogin() {
        Toast.makeText(myContext,"This is Anuthorized User",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProcessError(String error_string) {
        Toast.makeText(myContext,"This is Process error ",Toast.LENGTH_SHORT).show();
    }

}
