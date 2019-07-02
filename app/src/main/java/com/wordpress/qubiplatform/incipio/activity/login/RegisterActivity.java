package com.wordpress.qubiplatform.incipio.activity.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.activity.ConnectionActivity;
import com.wordpress.qubiplatform.incipio.firebase.entity.User;

public class RegisterActivity extends BaseActivity {

    //fields
    private EditText ime;
    private EditText prezime;
    private EditText email;
    private EditText sifra;
    private EditText sifra2;

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //dohvatanje polja
        ime=findViewById(R.id.regIme);
        prezime=findViewById(R.id.regPrezime);
        email=findViewById(R.id.regMail);
        sifra=findViewById(R.id.regPass);
        sifra2=findViewById(R.id.regPass2);

        Auth = FirebaseAuth.getInstance();
    }

    public void register(View view) {
        //valiadate form
        if(validateForm()){
            //kod za registraciju
            showProgressDialog();
            Auth.createUserWithEmailAndPassword(email.getText().toString(), sifra.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    hideProgressDialog();
                    if (task.isSuccessful()) {

                        Log.d("EmailPassword", "createUserWithEmail:success");
                        //add custom fields
                        FirebaseUser FB_user=Auth.getCurrentUser();
                        User user=new User(FB_user.getEmail(),ime.getText().toString(),prezime.getText().toString());
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(FB_user.getUid())
                                .setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //TODO back to sign in ... email verification?
                                Toast.makeText(getApplicationContext(),"Korisnik uspesno registrovan",Toast.LENGTH_LONG).show();
                                try {
                                    Thread.sleep(1800);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                openHome();
                            }
                        });
                    } else {

                        Log.w("EmailPassword", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Greska prilikom registracije.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void openHome(){
                Intent intent=new Intent(this, ConnectionActivity.class);
                startActivity(intent);
    }

    private boolean validateForm(){
        boolean valid=true;

        if(ime.getText().toString().isEmpty()){
            ime.setError("Ime je obavezno");
            valid=false;
        }
        if(prezime.getText().toString().isEmpty()){
            prezime.setError("Prezime je obavezno");
            valid=false;
        }
        if(email.getText().toString().isEmpty()){
            email.setError("Email je obavezan");
            valid=false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Email format nije ispravan");
            valid=false;
        }
        if(sifra.getText().toString().isEmpty()){
            sifra.setError("Sifra je obavezna");
            valid=false;
        }
        else if(sifra.getText().toString().length()<6){
            sifra.setError("Minimalna duzina je 6 karaktera.");
            valid=false;
        }
        if(sifra2.getText().toString().isEmpty()){
            sifra2.setError("Unesite ponovo sifru");
            valid=false;
        }
        if(!sifra.getText().toString().isEmpty()){
            if(!sifra.getText().toString().equals(sifra2.getText().toString())){
                sifra2.setError("Sifre nije ista");
                valid=false;
            }
        }

        return valid;
    }
}
