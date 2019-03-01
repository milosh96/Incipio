package com.wordpress.qubiplatform.incipio.firebase;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

class FBRepository {

    //reference

    private static FirebaseDatabase firebaseDatabase;

    private DatabaseReference myRef;

    public FBRepository(Context context){
        if(firebaseDatabase==null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
    }

    public LiveData<List<Game>> getGames(){
        //dohvatamo listu svih utakmica sa statusom aktiv?
        //TODO not working
        LiveData<List<Game>> games=new LiveData<List<Game>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<List<Game>> observer) {
                super.observe(owner, observer);
            }
        };

        myRef=firebaseDatabase.getReference("game");
        myRef.orderByChild("channel");

        return games;
    }
}
