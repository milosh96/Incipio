package com.wordpress.qubiplatform.incipio.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.BonusViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;
import com.wordpress.qubiplatform.incipio.util.BonusRecyclerAdapter;

import java.util.List;

public class BonusActivity extends AppCompatActivity implements BonusViewModel.BonusUpdate {

    private BonusRecyclerAdapter adapter;
    private BonusViewModel bonusViewModel;
    private RecyclerView bonusRecycle;
    private String gameId="";

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

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

        adapter=new BonusRecyclerAdapter();
        bonusViewModel=ViewModelProviders.of(this).get(BonusViewModel.class);
        bonusViewModel.setListener(this);

        bonusRecycle=findViewById(R.id.bonus_recycle);
        bonusRecycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bonusRecycle.setAdapter(adapter);

        //todo moglo je da se ubaci u viewmodel
        Auth=FirebaseAuth.getInstance();

        //add get auth
        final FirebaseUser currentUser = Auth.getCurrentUser();
        if(currentUser!=null) {
            String userId = currentUser.getUid();
            bonusViewModel.getQuizzes(gameId, userId);
        }
        else{
            //TODO empty list
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
    public void setQuiz(List<Quiz> quizzes) {
        adapter.setQuiz(quizzes);
    }

    @Override
    public void setQReplySimp(Quiz quiz, String reply) {
        //empty
    }

    @Override
    public void setQReplySel(Quiz quiz, int reply) {
        //empty
    }

    @Override
    public void setQReplyRat(Quiz quiz, int star1, int star2) {
        //empty
    }

    @Override
    public void setNoReply(Quiz quiz) {
        //empty
    }
}
