package com.wordpress.qubiplatform.incipio.activity.quiz;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.arch.lifecycle.ViewModelProviders;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.BonusViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;

import java.util.List;

public class RatingActivity extends AppCompatActivity implements BonusViewModel.BonusUpdate {


    private String log_tag="RatingActivity";
    private String quizId="";
    private BonusViewModel bonusViewModel;

    //comm 1
    private ImageView star_1;
    private ImageView star_2;
    private ImageView star_3;
    private ImageView star_4;
    private ImageView star_5;

    //comm 2
    private ImageView star_12;
    private ImageView star_22;
    private ImageView star_32;
    private ImageView star_42;
    private ImageView star_52;

    //only one rating
    private boolean commentator_one=false;
    private boolean commentator_two=false;
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

        star_1=findViewById(R.id.star_1);
        star_2=findViewById(R.id.star_2);
        star_3=findViewById(R.id.star_3);
        star_4=findViewById(R.id.star_4);
        star_5=findViewById(R.id.star_5);

        star_12=findViewById(R.id.star_12);
        star_22=findViewById(R.id.star_22);
        star_32=findViewById(R.id.star_32);
        star_42=findViewById(R.id.star_42);
        star_52=findViewById(R.id.star_52);

        //TODO add get auth
        String userId="AAy1PoVw27Ed5sdljaiY";
        bonusViewModel.getQuizAndAnswer(quizId, userId);

    }

    public void commentator1(View view) {
        int id=view.getId();
        if(!commentator_one) {
            commentator_one=true;
            switch (id) {
                case R.id.star_1: {
                    star_1.setImageResource(R.drawable.star_full);
                    star_2.setImageResource(R.drawable.star_empty);
                    star_3.setImageResource(R.drawable.star_empty);
                    star_4.setImageResource(R.drawable.star_empty);
                    star_5.setImageResource(R.drawable.star_empty);
                    rateCommentator(1,1);
                    break;
                }
                case R.id.star_2: {
                    star_1.setImageResource(R.drawable.star_full);
                    star_2.setImageResource(R.drawable.star_full);
                    star_3.setImageResource(R.drawable.star_empty);
                    star_4.setImageResource(R.drawable.star_empty);
                    star_5.setImageResource(R.drawable.star_empty);
                    rateCommentator(1,2);
                    break;
                }
                case R.id.star_3: {
                    star_1.setImageResource(R.drawable.star_full);
                    star_2.setImageResource(R.drawable.star_full);
                    star_3.setImageResource(R.drawable.star_full);
                    star_4.setImageResource(R.drawable.star_empty);
                    star_5.setImageResource(R.drawable.star_empty);
                    rateCommentator(1,3);
                    break;
                }
                case R.id.star_4: {
                    star_1.setImageResource(R.drawable.star_full);
                    star_2.setImageResource(R.drawable.star_full);
                    star_3.setImageResource(R.drawable.star_full);
                    star_4.setImageResource(R.drawable.star_full);
                    star_5.setImageResource(R.drawable.star_empty);
                    rateCommentator(1,4);
                    break;
                }
                case R.id.star_5: {
                    star_1.setImageResource(R.drawable.star_full);
                    star_2.setImageResource(R.drawable.star_full);
                    star_3.setImageResource(R.drawable.star_full);
                    star_4.setImageResource(R.drawable.star_full);
                    star_5.setImageResource(R.drawable.star_full);
                    rateCommentator(1,5);
                    break;
                }
            }
        }
    }

    public void commentator2(View view) {
        //to do check visibility, comm 2 exists?

        int id=view.getId();

        if(!commentator_two) {
            commentator_two=true;
            switch (id) {
                case R.id.star_12: {
                    star_12.setImageResource(R.drawable.star_full);
                    star_22.setImageResource(R.drawable.star_empty);
                    star_32.setImageResource(R.drawable.star_empty);
                    star_42.setImageResource(R.drawable.star_empty);
                    star_52.setImageResource(R.drawable.star_empty);
                    rateCommentator(2,1);
                    break;
                }
                case R.id.star_22: {
                    star_12.setImageResource(R.drawable.star_full);
                    star_22.setImageResource(R.drawable.star_full);
                    star_32.setImageResource(R.drawable.star_empty);
                    star_42.setImageResource(R.drawable.star_empty);
                    star_52.setImageResource(R.drawable.star_empty);
                    rateCommentator(2,2);
                    break;
                }
                case R.id.star_32: {
                    star_12.setImageResource(R.drawable.star_full);
                    star_22.setImageResource(R.drawable.star_full);
                    star_32.setImageResource(R.drawable.star_full);
                    star_42.setImageResource(R.drawable.star_empty);
                    star_52.setImageResource(R.drawable.star_empty);
                    rateCommentator(2,3);
                    break;
                }
                case R.id.star_42: {
                    star_12.setImageResource(R.drawable.star_full);
                    star_22.setImageResource(R.drawable.star_full);
                    star_32.setImageResource(R.drawable.star_full);
                    star_42.setImageResource(R.drawable.star_full);
                    star_52.setImageResource(R.drawable.star_empty);
                    rateCommentator(2,4);
                    break;
                }
                case R.id.star_52: {
                    star_12.setImageResource(R.drawable.star_full);
                    star_22.setImageResource(R.drawable.star_full);
                    star_32.setImageResource(R.drawable.star_full);
                    star_42.setImageResource(R.drawable.star_full);
                    star_52.setImageResource(R.drawable.star_full);
                    rateCommentator(2,5);
                    break;
                }
            }
        }
    }

    private void rateCommentator(int order, int grade){
        //TODO call bonusview.rate with commentatorId and grade
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
