package com.wordpress.qubiplatform.incipio.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wordpress.qubiplatform.incipio.firebase.entity.Chat;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;
import com.wordpress.qubiplatform.incipio.firebase.entity.QuizReply;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BonusRepository {

    private static final String log_tag="BonusRepository";
    //reference

    private static FirebaseDatabase firebaseDatabase;

    private BonusViewModel bonusViewModel;

    private List<Quiz> quizzes=new ArrayList<>();


    public BonusRepository(Context context, BonusViewModel bonusViewModel){
        if(firebaseDatabase==null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        this.bonusViewModel=bonusViewModel;
    }

    public void getColor(String userId){
        DatabaseReference myRef=firebaseDatabase.getReference("quizReply");

        myRef.orderByChild("idUser").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot oneGame:dataSnapshot.getChildren()){
                    //key value parovi
                    String id=oneGame.getKey();
                    QuizReply quizReply=oneGame.getValue(QuizReply.class);
                    quizReply.setId(id);
                    for(Quiz quiz:quizzes){
                        if(quiz.getId().equals(quizReply.getIdQuiz())){
                            quiz.setColor("red");
                        }
                        else{
                            //nothing color already set to green by default
                        }
                    }

                }
                bonusViewModel.setQuizzes(quizzes);
                //Collections.reverse(forum);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO log i ispis korsniku
                Log.d(log_tag,"Greska kod dohvatanja poruke");
            }
        });
    }

    public void getQuiz(String gameId, final String userId){
        DatabaseReference myRef=firebaseDatabase.getReference("quiz");

        myRef.orderByChild("idGame").equalTo(gameId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //kreiramo listu i posaljemo u viewmodel
                quizzes.clear();
                for (DataSnapshot oneGame:dataSnapshot.getChildren()){
                    //key value parovi
                    String id=oneGame.getKey();
                    Quiz quiz=oneGame.getValue(Quiz.class);
                    quiz.setId(id);
                    //not finished!
                    quizzes.add(quiz);
                }
                //Collections.reverse(quizzes);
                getColor(userId);
                //bonusViewModel.setQuizzes(quizzes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO log i ispis korsniku
                Log.d(log_tag,"Greska kod dohvatanja poruke");
            }
        });
    }

    public void getQReply(final String quizId, final String userId){
        DatabaseReference myRef=firebaseDatabase.getReference("quiz");

        myRef.child(quizId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(log_tag,"Quiz with id "+quizId+" found.");

                Log.d("FIREBASE","Before quiz");
                Quiz quiz=dataSnapshot.getValue(Quiz.class);
                Log.d("FIREBASE","After quiz");
                quiz.setId(quizId);

                findQReply(quiz,userId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //empty
                Log.d(log_tag,"Database error "+databaseError.toString());
            }
        });
    }

    private void findQReply(final Quiz quiz, String userId){
        DatabaseReference myRef=firebaseDatabase.getReference("quizReply");

        myRef.orderByChild("idUser").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int type=0;
                int reply=0;
                String answer="";
                int star1=1;
                int star2=1;
                for (DataSnapshot oneQuiz:dataSnapshot.getChildren()){
                    //key value parovi
                    String id=oneQuiz.getKey();
                    QuizReply quizReply=oneQuiz.getValue(QuizReply.class);
                    quizReply.setId(id);

                    if(quiz.getId().equals(quizReply.getIdQuiz())){
                        //nadjen odgovor
                        switch (quiz.getType()){
                            case "select":{

                                reply=Integer.parseInt(quizReply.getReply());
                                type=1;
                                break;
                            }
                            case "simple":{

                                answer=quizReply.getReply();
                                type=2;
                                break;
                            }
                            case "rating":{
                                //moglo je malo lepse
                                String[] comm=quizReply.getReply().split(":");
                                star1=Integer.parseInt(comm[0]);
                                star2=Integer.parseInt(comm[1]);
                                type=3;
                                break;
                            }
                        }
                        break;
                    }

                }

                switch (type){
                    case 0:{
                        bonusViewModel.setNoReply(quiz);
                        break;
                    }
                    case 1:{
                        //select
                        bonusViewModel.setQReplySel(quiz,reply);
                        break;
                    }
                    case 2:{
                        //simple
                        bonusViewModel.setQReplySimp(quiz,answer);
                        break;
                    }
                    case 3:{
                        //rat
                        bonusViewModel.setQReplyRat(quiz,star1,star2);
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(log_tag,"Database error "+databaseError.toString());
            }
        });
    }


    public void addReply(String quizId, String userId, String reply){
        DatabaseReference myRef=firebaseDatabase.getReference("quizReply");

        //todo smisliti cemu sluzi status
        myRef.push().setValue(new QuizReply(quizId,userId,reply,"zabelezeno"));
    }
}
