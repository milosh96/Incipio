package com.wordpress.qubiplatform.incipio.firebase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;

import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;

import java.util.List;

public class BonusViewModel extends AndroidViewModel {

    private BonusRepository bonusRepository;

    private BonusUpdate listener;

    public BonusViewModel(@NonNull Application application) {
        super(application);
        bonusRepository=new BonusRepository(application, this);
    }

    public void setListener(Context context){
        if(context instanceof BonusUpdate){
            listener=(BonusUpdate) context;
        }
        else{
            throw new RuntimeException("Error with compatibility. Activity doesnt implement interface BonusUpdate");
        }
    }

    public interface BonusUpdate{
        void setQuiz(List<Quiz> quizzes);

    }


    public void getQuizzes(String gameId, String userId){
        bonusRepository.getQuiz(gameId,userId);
    }

    public void getQuizAndAnswer(String quizId, String userId){

    }


    public void setQuizzes(List<Quiz> quizzes){
        listener.setQuiz(quizzes);
    }


}
