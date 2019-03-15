package com.wordpress.qubiplatform.incipio.activity.quiz;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.BonusViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;

import java.util.List;

public class RatingActivity extends AppCompatActivity implements BonusViewModel.BonusUpdate {


    private String log_tag="RatingActivity";
    private String quizId="";
    private BonusViewModel bonusViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Log.d(log_tag,"Game activity entered");
        final Bundle data=getIntent().getExtras();

        if(data!=null){
            quizId=data.getString("QUIZ_ID");
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

        bonusViewModel=ViewModelProviders.of(this).get(BonusViewModel.class);
        bonusViewModel.setListener(this);

        //TODO add get auth
        String userId="AAy1PoVw27Ed5sdljaiY";
       // bonusViewModel.ch

    }

    public void commentator1(View view) {
        int id=view.getId();

        switch (id){
            case R.id.star_1:{

                break;
            }
            case R.id.star_2:{

                break;
            }
            case R.id.star_3:{

                break;
            }
            case R.id.star_4:{

                break;
            }
            case R.id.star_5:{

                break;
            }
        }
    }

    public void commentator2(View view) {
        //to do check visibility, comm 2 exists?

        int id=view.getId();

        switch (id){
            case R.id.star_12:{

                break;
            }
            case R.id.star_22:{

                break;
            }
            case R.id.star_32:{

                break;
            }
            case R.id.star_42:{

                break;
            }
            case R.id.star_52:{

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
    public void setQuiz(List<Quiz> quizzes) {
        //empty
    }
}
