package com.wordpress.qubiplatform.incipio.firebase;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

class FBRepository {

    private static final String log_tag="FBRepository";
    //reference

    private static FirebaseDatabase firebaseDatabase;

    private FBViewModel fbViewModel;

    private List<Game> games=new ArrayList<>();

    public FBRepository(Context context, FBViewModel fbViewModel){
        if(firebaseDatabase==null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        this.fbViewModel=fbViewModel;
    }

    public void getGames(){
        //dohvatamo listu svih utakmica sa statusom aktiv?
        //TODO not working

        DatabaseReference myRef=firebaseDatabase.getReference("game");
        myRef.orderByChild("channel");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //kreiramo listu i posaljemo u viewmodel
                games.clear();
                for (DataSnapshot oneGame:dataSnapshot.getChildren()){
                    //key value parovi
                    String id=oneGame.getKey();
                    Game game=oneGame.getValue(Game.class);
                    game.setId(id);
                    games.add(game);
                }
                fbViewModel.setGames(games);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO log i ispis korsniku
                Log.d(log_tag,"Greska kod dohvatanja utakmica");
            }
        });
    }

    public void getGame(final String id){
        //TODO security check
        DatabaseReference myRef=firebaseDatabase.getReference("game");
        myRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //TODO check if cond good and add return
                Log.d(log_tag,"Game with id : "+id);
                Game game= dataSnapshot.getValue(Game.class);
                game.setId(id);
                fbViewModel.setGame(game);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //nothing TODO add logging
            }
        });
    }

    public void sendDM(String gameId, String userId, String title, String body){
        //TODO
        /**
         * nova klasa MAIl
         * odradi push
         * dodaj polja
         */
    }
}
