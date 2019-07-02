package com.wordpress.qubiplatform.incipio.activity.quiz;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.BonusViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;

import java.util.List;

public class SimpleActivity extends AppCompatActivity implements BonusViewModel.BonusUpdate {

    private String quizId="";
    private String userId="";
    private BonusViewModel bonusViewModel;
    private int position=1;
    private FirebaseAuth Auth;

    //fields
    private TextView desc;
    private EditText reply_e;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

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

        desc=findViewById(R.id.quiz_description);
        reply_e=findViewById(R.id.quiz_reply);
        save=findViewById(R.id.save_answer);

        Auth=FirebaseAuth.getInstance();
        final FirebaseUser currentUser = Auth.getCurrentUser();
        if(currentUser!=null) {
            userId = currentUser.getUid();
            bonusViewModel.getQReply(quizId, userId);
        }
        else{
            //empty view
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
        addQuizInfo(quiz);

        reply_e.setText(reply);
        reply_e.setClickable(false);
        save.setClickable(false);
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
        addQuizInfo(quiz);
    }

    private void addQuizInfo(Quiz quiz){
        desc.setText(position+". "+quiz.getQuestion());
    }

    public void save_answer(View view) {
        String reply=reply_e.getText().toString();

        if(!reply.trim().equals("")) {
            bonusViewModel.addReply(quizId, userId, reply);

            Toast.makeText(getApplicationContext(), "Vas odgovor je zabelezen.", Toast.LENGTH_LONG).show();
            reply_e.setClickable(false);
            save.setClickable(false);
        }
        else{
            Toast.makeText(getApplicationContext(), "Unesite odgovor.", Toast.LENGTH_LONG).show();
        }
    }
}
