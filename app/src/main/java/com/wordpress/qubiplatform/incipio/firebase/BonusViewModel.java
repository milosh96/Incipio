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
        void setQReplySimp(Quiz quiz, String reply);
        void setQReplySel(Quiz quiz, int reply);
        void setQReplyRat(Quiz quiz, int star1, int star2);
        void setNoReply(Quiz quiz);
    }


    public void getQuizzes(String gameId, String userId){
        bonusRepository.getQuiz(gameId,userId);
    }


    public void getQReply(String quizId, String userId){
        bonusRepository.getQReply(quizId,userId);
    }

    public void addReply(String quizId, String userId, String reply){
        bonusRepository.addReply(quizId,userId,reply);
    }

    public void setQuizzes(List<Quiz> quizzes){
        listener.setQuiz(quizzes);
    }

    public void setQReplySimp(Quiz q, String rep){
        listener.setQReplySimp(q,rep);
    }
    public void setQReplySel(Quiz q, int rep){
        listener.setQReplySel(q,rep);
    }
    public void setQReplyRat(Quiz q, int rep1,int rep2){
        listener.setQReplyRat(q,rep1,rep2);
    }
    public void setNoReply(Quiz q){listener.setNoReply(q);};

}
