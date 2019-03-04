package com.wordpress.qubiplatform.incipio.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.FBViewModel;

public class ForumActivity extends AppCompatActivity {

    public static final String log_tag="DirectMssg";
    private String gameId;
    private FBViewModel fbViewModel;

    private EditText chatMssg;
    private ImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

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

        chatMssg=findViewById(R.id.chat_mssg);
        send=findViewById(R.id.chat_btn);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //formiranje intenta i slanje poruke(uspesan toast)

                String poruka=chatMssg.getText().toString();

                String userId="";
                //TODO get with auth

                if(poruka.trim().equals("")){
                    Toast.makeText(v.getContext(),"Telo poruke je obavezno.",Toast.LENGTH_LONG).show();
                    return;
                }

                fbViewModel.sendChat(gameId,userId,  poruka);
            }
        });

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
