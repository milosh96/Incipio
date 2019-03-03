package com.wordpress.qubiplatform.incipio.activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.FBViewModel;
import com.wordpress.qubiplatform.incipio.firebase.Game;

import java.util.List;

public class GameActivity extends AppCompatActivity implements FBViewModel.DataUpdate {

    private static final String log_tag="GameActivity";
    public static final int BONUS_ACTIVITY=12345;
    public static final int FORUM_ACTIVITY=54321;
    public static final int DM_ACTIVITY=8765;

    private String gameId="";

    //image views
    private ImageView imageMssg;
    private ImageView imageChat;
    private ImageView imageBonus;

    private FBViewModel fbViewModel;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.d(log_tag,"Game activity entered");
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

        fbViewModel.getGame(gameId);

        //image onClick

        imageBonus=findViewById(R.id.image_bonus);
        imageChat=findViewById(R.id.image_chat);
        imageMssg=findViewById(R.id.image_message);


        imageBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Intent
                Intent intent=new Intent(v.getContext(),BonusActivity.class);
                intent.putExtra("GAME_ID",gameId);
                ((Activity) v.getContext()).startActivityForResult(intent,BONUS_ACTIVITY);
            }
        });

        imageMssg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent for direct message
                Intent intent=new Intent(v.getContext(),DirectMssgActivity.class);
                intent.putExtra("GAME_ID",gameId);

                ((Activity) v.getContext()).startActivityForResult(intent,DM_ACTIVITY);
            }
        });

        imageChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent for public forum
                Intent intent=new Intent(v.getContext(),ForumActivity.class);
                intent.putExtra("GAME_ID",gameId);
                ((Activity) v.getContext()).startActivityForResult(intent,FORUM_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case FORUM_ACTIVITY:{

                if(requestCode==RESULT_OK){

                }
                else{

                }

                break;
            }

            case DM_ACTIVITY:{

                if(requestCode==RESULT_OK){
                    //make toast
                    Toast.makeText(getApplicationContext(),"Poruka uspesno poslata", Toast.LENGTH_LONG);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Poruka neuspesno poslata", Toast.LENGTH_LONG);
                }

                break;
            }

            case BONUS_ACTIVITY:{

                if(requestCode==RESULT_OK){

                }
                else{

                }

                break;
            }
        }
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
        //empty here
    }

    @Override
    public void setGame(Game game) {
        this.game=game;
        TextView desc=findViewById(R.id.game_description);
        desc.setText(game.getDescription());
    }
}
