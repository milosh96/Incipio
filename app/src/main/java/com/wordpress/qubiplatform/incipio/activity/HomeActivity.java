package com.wordpress.qubiplatform.incipio.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.entity.Chat;
import com.wordpress.qubiplatform.incipio.firebase.FBViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Game;
import com.wordpress.qubiplatform.incipio.util.HomeRecyclerAdapter;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements FBViewModel.DataUpdate {

    private TextView mTextMessage;

    private RecyclerView myRecyclerView;

    private FBViewModel fbViewModel;
    private HomeRecyclerAdapter adapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //prepravka: KREIRANJE intentova i pozivanje odgovarajucih activity-a
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_message:
                    mTextMessage.setText(R.string.title_message);
                    return true;
                case R.id.navigation_standings:
                    mTextMessage.setText(R.string.title_standings);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        //TODO dohvatanje svih live utakmica i ispis
        /**
         * dohvatanje ScrollView-a  X
         * dohvatanje podataka iz baze(sa live izmenama)
         * dinamicko filovanje u Scroll
         * dodoavanje dugmeta koji poziva ispit jedne utakmice
         */


        fbViewModel= ViewModelProviders.of(this).get(FBViewModel.class);
        fbViewModel.setListener(this);
        adapter=new HomeRecyclerAdapter();

        myRecyclerView=findViewById(R.id.home_recycle);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        fbViewModel.getGames().observe(this, new Observer<List<Game>>() {
//            @Override
//            public void onChanged(@Nullable List<Game> games) {
//                //TODO optimizacija loadovanja
//                /**
//                 * umesto set games, moze Toast koji kaze refreshujte povlacenjem na dole
//                 */
//                adapter.setGames(games);
//            }
//        });

        fbViewModel.getGames();
    }

    @Override
    public void setGames(List<Game> games) {
        adapter.setGames(games);
    }

    @Override
    public void setGame(Game game) {
        //empty here
    }

    @Override
    public void setForum(List<Chat> forum) {
        //empty
    }
}
