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

    public void getGames(){
        fbRepository.getGames();
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
    }
}
