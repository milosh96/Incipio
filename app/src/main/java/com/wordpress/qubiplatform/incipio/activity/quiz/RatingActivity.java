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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.BonusViewModel;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;

import java.util.List;

public class RatingActivity extends AppCompatActivity implements BonusViewModel.BonusUpdate {


    private String log_tag="RatingActivity";
    private String quizId="";
    private String userId="";
    private BonusViewModel bonusViewModel;

    private TextView desc;
    private TextView text_c1;
    private TextView text_c2;
    private int position=1;
    private FirebaseAuth Auth;

    private int grade_1=1;
    private int grade_2=1;

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
            position=data.getInt("ORDER");
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

        desc=findViewById(R.id.rating_description);
        desc.setText(position+". Ocenite komentatora");
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


        text_c1=findViewById(R.id.commentator_name);
        text_c2=findViewById(R.id.commentator_name2);

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

    private void star11(){
        star_1.setImageResource(R.drawable.star_full);
        star_2.setImageResource(R.drawable.star_empty);
        star_3.setImageResource(R.drawable.star_empty);
        star_4.setImageResource(R.drawable.star_empty);
        star_5.setImageResource(R.drawable.star_empty);
    }

    private void star12(){
        star_1.setImageResource(R.drawable.star_full);
        star_2.setImageResource(R.drawable.star_full);
        star_3.setImageResource(R.drawable.star_empty);
        star_4.setImageResource(R.drawable.star_empty);
        star_5.setImageResource(R.drawable.star_empty);
    }
    private void star13(){
        star_1.setImageResource(R.drawable.star_full);
        star_2.setImageResource(R.drawable.star_full);
        star_3.setImageResource(R.drawable.star_full);
        star_4.setImageResource(R.drawable.star_empty);
        star_5.setImageResource(R.drawable.star_empty);
    }
    private void star14(){
        star_1.setImageResource(R.drawable.star_full);
        star_2.setImageResource(R.drawable.star_full);
        star_3.setImageResource(R.drawable.star_full);
        star_4.setImageResource(R.drawable.star_full);
        star_5.setImageResource(R.drawable.star_empty);
    }
    private void star15(){
        star_1.setImageResource(R.drawable.star_full);
        star_2.setImageResource(R.drawable.star_full);
        star_3.setImageResource(R.drawable.star_full);
        star_4.setImageResource(R.drawable.star_full);
        star_5.setImageResource(R.drawable.star_full);
    }

    public void commentator1(View view) {
        int id=view.getId();
        if(!commentator_one) {
            commentator_one=true;
            switch (id) {
                case R.id.star_1: {
                    grade_1=1;
                    star11();
                    if(commentator_two) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_2: {
                    grade_1=2;
                    star12();
                    if(commentator_two) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_3: {
                    grade_1=3;
                    star13();
                    if(commentator_two) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_4: {
                    grade_1=4;
                    star14();
                    if(commentator_two) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_5: {
                    grade_1=5;
                    star15();
                    if(commentator_two) {
                        rateCommentator();
                    }
                    break;
                }
            }
        }
    }

    private void star21(){
        star_12.setImageResource(R.drawable.star_full);
        star_22.setImageResource(R.drawable.star_empty);
        star_32.setImageResource(R.drawable.star_empty);
        star_42.setImageResource(R.drawable.star_empty);
        star_52.setImageResource(R.drawable.star_empty);
    }
    private void star22(){
        star_12.setImageResource(R.drawable.star_full);
        star_22.setImageResource(R.drawable.star_full);
        star_32.setImageResource(R.drawable.star_empty);
        star_42.setImageResource(R.drawable.star_empty);
        star_52.setImageResource(R.drawable.star_empty);
    }
    private void star23(){
        star_12.setImageResource(R.drawable.star_full);
        star_22.setImageResource(R.drawable.star_full);
        star_32.setImageResource(R.drawable.star_full);
        star_42.setImageResource(R.drawable.star_empty);
        star_52.setImageResource(R.drawable.star_empty);
    }
    private void star24(){
        star_12.setImageResource(R.drawable.star_full);
        star_22.setImageResource(R.drawable.star_full);
        star_32.setImageResource(R.drawable.star_full);
        star_42.setImageResource(R.drawable.star_full);
        star_52.setImageResource(R.drawable.star_empty);
    }
    private void star25(){
        star_12.setImageResource(R.drawable.star_full);
        star_22.setImageResource(R.drawable.star_full);
        star_32.setImageResource(R.drawable.star_full);
        star_42.setImageResource(R.drawable.star_full);
        star_52.setImageResource(R.drawable.star_full);
    }

    public void commentator2(View view) {
        //to do check visibility, comm 2 exists?

        int id=view.getId();

        if(!commentator_two) {
            commentator_two=true;
            switch (id) {
                case R.id.star_12: {
                    grade_2=1;
                    star21();
                    if(commentator_one) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_22: {
                    grade_2=2;
                    star22();
                    if(commentator_one) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_32: {
                    grade_2=3;
                    star23();
                    if(commentator_one) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_42: {
                    grade_2=5;
                    star24();
                    if(commentator_one) {
                        rateCommentator();
                    }
                    break;
                }
                case R.id.star_52: {
                    grade_2=5;
                    star25();
                    if(commentator_one) {
                        rateCommentator();
                    }
                    break;
                }
            }
        }
    }

    private void rateCommentator(){
        bonusViewModel.addReply(quizId,userId,grade_1+":"+grade_2);
        Toast.makeText(getApplicationContext(),"Vas odgovor je zabelezen.",Toast.LENGTH_LONG).show();
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
        //empty
    }

    @Override
    public void setQReplyRat(Quiz quiz, int star1, int star2) {
        addQuizInfo(quiz);

        commentator_one=true;
        commentator_two=true;

        //star1
        if(star1==1){
            star11();
        }
        else if(star1==2){
            star12();
        }
        else if(star1==3){
            star13();
        }
        else if(star1==4){
            star14();
        }
        else{
            star15();
        }

        //star2
        if(star2==1){
            star21();
        }
        else if(star2==2){
            star22();
        }
        else if(star2==3){
            star23();
        }
        else if(star2==4){
            star24();
        }
        else{
            star25();
        }
    }

    @Override
    public void setNoReply(Quiz quiz) {
        addQuizInfo(quiz);
    }

    private void addQuizInfo(Quiz quiz){
        text_c1.setText(quiz.getName_1());
        text_c2.setText(quiz.getName_2());
    }
}
