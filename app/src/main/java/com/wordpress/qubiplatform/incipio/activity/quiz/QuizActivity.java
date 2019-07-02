package com.wordpress.qubiplatform.incipio.activity.quiz;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.BonusViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements BonusViewModel.BonusUpdate {

    private String quizId;
    private int position;
    private BonusViewModel bonusViewModel;
    private FirebaseAuth Auth;
    private String userId="";
    private boolean answered=false;

    //fields
    private TextView desc;
    private TextView text_a1;
    private TextView text_a2;
    private TextView text_a3;
    private TextView text_a4;
    private CardView card_a1;
    private CardView card_a2;
    private CardView card_a3;
    private CardView card_a4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final Bundle data=getIntent().getExtras();

        if(data!=null){
            quizId=data.getString("QUIZ_ID");
            position=data.getInt("ORDER",1);
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

        //fields
        desc=findViewById(R.id.quiz_description);
        text_a1=findViewById(R.id.text_answer1);
        text_a2=findViewById(R.id.text_answer2);
        text_a3=findViewById(R.id.text_answer3);
        text_a4=findViewById(R.id.text_answer4);
        card_a1=findViewById(R.id.card_answer1);
        card_a2=findViewById(R.id.card_answer2);
        card_a3=findViewById(R.id.card_answer3);
        card_a4=findViewById(R.id.card_answer4);

        card_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    bonusViewModel.addReply(quizId,userId,0+"");
                    answered=true;
                    addAnswer(0);
                    //TODO ovaj toast pomeriti u listener BonusViewModela...
                    //da ne stigne odmah odgovor, nego tek kad se zabelezi!
                    Toast.makeText(getApplicationContext(),"Vas odgovor je zabelezen!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Odgovor je vec zabelezen.",Toast.LENGTH_LONG).show();
                }
            }
        });
        card_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    bonusViewModel.addReply(quizId,userId,1+"");
                    answered=true;
                    addAnswer(1);
                    Toast.makeText(getApplicationContext(),"Vas odgovor je zabelezen!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Odgovor je vec zabelezen.",Toast.LENGTH_LONG).show();
                }
            }
        });
        card_a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    bonusViewModel.addReply(quizId,userId,2+"");
                    answered=true;
                    addAnswer(2);
                    Toast.makeText(getApplicationContext(),"Vas odgovor je zabelezen!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Odgovor je vec zabelezen.",Toast.LENGTH_LONG).show();
                }
            }
        });
        card_a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    bonusViewModel.addReply(quizId,userId,3+"");
                    answered=true;
                    addAnswer(3);
                    Toast.makeText(getApplicationContext(),"Vas odgovor je zabelezen!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Odgovor je vec zabelezen.",Toast.LENGTH_LONG).show();
                }
            }
        });


        Auth=FirebaseAuth.getInstance();
        final FirebaseUser currentUser = Auth.getCurrentUser();
        if(currentUser!=null) {
            userId = currentUser.getUid();
            bonusViewModel.getQReply(quizId,userId);
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
        //empty
    }

    @Override
    public void setQReplySimp(Quiz quiz, String reply) {
        //empty
    }

    @Override
    public void setQReplySel(Quiz quiz, int reply) {
        setQuizInfo(quiz);

        addAnswer(reply);
    }

    @Override
    public void setQReplyRat(Quiz quiz, int star1, int star2) {
        //empty
    }

    @Override
    public void setNoReply(Quiz quiz) {
        setQuizInfo(quiz);
    }
    private void setQuizInfo(Quiz quiz){
        desc.setText(position+". "+quiz.getQuestion());

        text_a1.setText(quiz.getGiven_0());
        text_a2.setText(quiz.getGiven_1());
        text_a3.setText(quiz.getGiven_2());
        text_a4.setText(quiz.getGiven_3());

    }
    private void addAnswer(int reply){
        //todo dodati ako je tacan da bude zeleno, a ako netacan crveno
        if(reply==0){
            text_a1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            text_a1.setTextColor(getResources().getColor(R.color.colorPrimary));
            text_a2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        }
        else if(reply==1){
            text_a2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            text_a2.setTextColor(getResources().getColor(R.color.colorPrimary));
            text_a1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        }
        else if(reply==2){
            text_a3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            text_a3.setTextColor(getResources().getColor(R.color.colorPrimary));
            text_a2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        }
        else{
            text_a4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            text_a4.setTextColor(getResources().getColor(R.color.colorPrimary));
            text_a2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            text_a1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        }

        card_a1.setClickable(false);
        card_a2.setClickable(false);
        card_a3.setClickable(false);
        card_a4.setClickable(false);
        answered=true;
    }
}
