package com.wordpress.qubiplatform.incipio.activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.FBViewModel;

public class DirectMssgActivity extends AppCompatActivity {

    public static final String log_tag="DirectMssg";
    private String gameId;
    private FBViewModel fbViewModel;

    //polja za unos
    private EditText title;
    private EditText body;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_mssg);

        Log.d(log_tag,"DirectMssg activity entered");
        final Bundle data=getIntent().getExtras();

        if(data!=null){
            gameId=data.getString("GAME_ID");
            //Toast.makeText(this,"Game id is something",Toast.LENGTH_LONG);
        }

        Toolbar toolbar = findViewById(R.id.game_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        fbViewModel= ViewModelProviders.of(this).get(FBViewModel.class);

        title=findViewById(R.id.mail_title);
        body=findViewById(R.id.mail_body);

        FloatingActionButton send=findViewById(R.id.mail_send);
        Auth=FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssg_title=title.getText().toString();
                String mssg_body=body.getText().toString();

                if(mssg_title.trim().equals("")||mssg_body.trim().equals("")){
                    Toast.makeText(v.getContext(),"Sva polja su obavezna",Toast.LENGTH_LONG).show();
                    return;
                }
                //TODO dohvatiti userId?
                final FirebaseUser currentUser = Auth.getCurrentUser();
                if(currentUser!=null) {
                    String userId = currentUser.getUid();

                    fbViewModel.sendDM(gameId, userId, mssg_title, mssg_body);

//                Intent intent=new Intent();
//                intent.putExtra("success_status","success");
                    setResult(RESULT_OK);
                    finish();
                }
                else{
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //TODO Kreiraj intent sa RESULT_CANCELED
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //prepravka: KREIRANJE intentova i pozivanje odgovarajucih activity-a
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_message:
                    // mTextMessage.setText(R.string.title_message);
                    return true;
                case R.id.navigation_standings:
                    //  mTextMessage.setText(R.string.title_standings);
                    return true;
                case R.id.navigation_profile:
                    // mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };
}
