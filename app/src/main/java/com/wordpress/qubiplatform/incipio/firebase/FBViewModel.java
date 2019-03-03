package com.wordpress.qubiplatform.incipio.firebase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class FBViewModel extends AndroidViewModel {
    private FBRepository fbRepository;
    private DataUpdate listener;
    private static final String log_tag="ViewModel";

    public FBViewModel(@NonNull Application application) {
        super(application);
        fbRepository=new FBRepository(application, this);

    }

    public void sendDM(String gameId, String userId, String title, String body){
        fbRepository.sendDM(gameId, userId, title, body);
    }

    public void getGames(){
        fbRepository.getGames();
    }

    public void getGame(String id){
        fbRepository.getGame(id);
    }

    public void setGame(Game game){
        listener.setGame(game);
    }

    public void setGames(List<Game> games){
        listener.setGames(games);
    }

    public void setListener(Context context){
        if(context instanceof DataUpdate){
            listener=(DataUpdate) context;
            Log.d(log_tag,"Successfully added listener");
        }
        else{
            throw new RuntimeException("Error with compatibility. Activity doesnt implement interface DataUpdate");
        }
    }

    public interface DataUpdate{
        void setGames(List<Game> games);
        void setGame(Game game);
    }
}
