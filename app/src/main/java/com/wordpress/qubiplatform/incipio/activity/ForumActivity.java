package com.wordpress.qubiplatform.incipio.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.entity.Chat;
import com.wordpress.qubiplatform.incipio.firebase.FBViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Game;
import com.wordpress.qubiplatform.incipio.util.ForumRecyclerAdapter;

import java.util.List;

public class ForumActivity extends AppCompatActivity implements FBViewModel.DataUpdate{

    public static final String log_tag="DirectMssg";
    private String gameId;
    private FBViewModel fbViewModel;

    private EditText chatMssg;
    private ImageButton send;

    //recycler
    private RecyclerView myRecyclerView;
    private ForumRecyclerAdapter adapter;

    private FirebaseAuth Auth;

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
        fbViewModel.setListener(this);

        chatMssg=findViewById(R.id.chat_mssg);
        send=findViewById(R.id.chat_btn);
        Auth=FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //formiranje intenta i slanje poruke(uspesan toast)

                String poruka=chatMssg.getText().toString();
                final FirebaseUser currentUser = Auth.getCurrentUser();
                if(currentUser!=null) {
                    String userId = currentUser.getUid();
                    //TODO get with auth

                    if (poruka.trim().equals("")) {
                        Toast.makeText(v.getContext(), "Telo poruke je obavezno.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    fbViewModel.sendChat(gameId, userId, poruka);
                    chatMssg.setText("");
                }
                else{
                    Toast.makeText(v.getContext(), "Problem sa autentikacijom korisnika.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //recycler
        adapter=new ForumRecyclerAdapter();
        myRecyclerView=findViewById(R.id.forumRecycler);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fbViewModel.getForum(gameId);

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

    @Override
    public void setGames(List<Game> games) {
        //empty
    }

    @Override
    public void setGame(Game game) {
        //empty
    }

    @Override
    public void setForum(List<Chat> forum) {
        //TODO update bolje uraditi
        adapter.setForum(forum);
    }
}
