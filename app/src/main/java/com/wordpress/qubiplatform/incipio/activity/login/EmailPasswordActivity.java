package com.wordpress.qubiplatform.incipio.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.activity.ConnectionActivity;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Veljko on 10.4.2018..
 */

public class EmailPasswordActivity extends BaseActivity implements View.OnClickListener{

    public static final String TAG="FB_LOGIN";

    private TextView statusTW;
    private TextView detailTW;
    private EditText emailField;
    private EditText passwordField;

    private FirebaseAuth Auth;
    private LoginButton fbLogin;
    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailpassword);

        statusTW = findViewById(R.id.status);
        detailTW = findViewById(R.id.detail);
        emailField = findViewById(R.id.fieldEmail);
        passwordField = findViewById(R.id.fieldPassword);

        findViewById(R.id.emailSignInButton).setOnClickListener(this);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
//        findViewById(R.id.signOutButton).setOnClickListener(this);
//        findViewById(R.id.verifyEmailB).setOnClickListener(this);

        Auth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        fbLogin=findViewById(R.id.fb_login_button);
        callbackManager=CallbackManager.Factory.create();
        fbLogin.setReadPermissions(Arrays.asList("email"));

        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FBLOGIN","Succes with FB");
                createFBCredential(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("FBLOGIN","User canceled FB login");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FBLOGIN","Error with FB Login!!!\n"+error.toString());
                Toast.makeText(getApplicationContext(),"Greska u komunikaciji sa serverom.",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = Auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void createAccount(String email, String password) {

        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);


        /*
        //Promena novi intent
        Log.d("EmailPassword", "createAccount: " + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Log.d("EmailPassword", "createUserWithEmail:success");
                    FirebaseUser user = Auth.getCurrentUser();
                    updateUI(user);
                } else {

                    Log.w("EmailPassword", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(EmailPasswordActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

                hideProgressDialog();
            }
        });
        */
    }

    private void signIn(String email, String password) {
        Log.d("EmailPassword", "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        Auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EmailPassword", "signInWithEmail:success");
                            FirebaseUser user = Auth.getCurrentUser();
                            //new intent connection activity
                            updateUI(user);
                            try {
                                Thread.sleep(1800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            openHome();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("EmailPassword", "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Greska pri autentifikaciji.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
//                        if (!task.isSuccessful()) {
//                            statusTW.setText(R.string.auth_failed);
//                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });

    }

    private void signOut() {
        Auth.signOut();
        updateUI(null);
    }

    private void openHome(){
        Intent intent=new Intent(this, ConnectionActivity.class);
        startActivity(intent);
    }


    /*
    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.verifyEmailB).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = Auth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.verifyEmailB).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(EmailPasswordActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("EmailPassword", "sendEmailVerification", task.getException());
                            Toast.makeText(EmailPasswordActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
*/


    private void updateUI(FirebaseUser user) {

        hideProgressDialog();
        /*
        if (user != null) {
            statusTW.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            detailTW.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            startActivity(new Intent(this, ConnectionActivity.class));
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            findViewById(R.id.verifyEmailB).setEnabled(!user.isEmailVerified());
        } else {
            statusTW.setText(R.string.signed_out);
            detailTW.setText(null);

            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
        */
    }


    private boolean validateForm() {

        boolean valid = true;

        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Obavezno polje.");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Obavezno polje.");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.emailCreateAccountButton) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.emailSignInButton) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createFBCredential(AccessToken accessToken) {
        AuthCredential credential=FacebookAuthProvider.getCredential(accessToken.getToken());
        Log.d("FBLOGIN","Creating token!!!");
        Auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FBLOGIN","Task status: "+task.toString());
                        if(task.isComplete()){
                            Intent intent=new Intent(getApplicationContext(),ConnectionActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Greska sa facebook login tokenom",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    
}
