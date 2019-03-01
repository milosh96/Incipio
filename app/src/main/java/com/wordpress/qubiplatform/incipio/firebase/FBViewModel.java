package com.wordpress.qubiplatform.incipio.firebase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FBViewModel extends AndroidViewModel {
    private FBRepository fbRepository;
    public FBViewModel(@NonNull Application application) {
        super(application);
        fbRepository=new FBRepository(application);
    }

    public LiveData<List<Game>> getGames(){

        return null;
    }
}
