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
import com.wordpress.qubiplatform.incipio.firebase.entity.Game;
import com.wordpress.qubiplatform.incipio.firebase.entity.Mail;
import com.wordpress.qubiplatform.incipio.firebase.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

class FBRepository {

    private static final String log_tag="FBRepository";
    //reference

    private static FirebaseDatabase firebaseDatabase;

    private FBViewModel fbViewModel;

    private List<Game> games=new ArrayList<>();

    private List<Chat> forum=new ArrayList<>();

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
        DatabaseReference myRef=firebaseDatabase.getReference("mail");

        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        myRef.push().setValue(new Mail(body,title,userId, gameId,timeStamp));

    }

    public void sendChat(String gameId, String userId, String poruka){
        DatabaseReference myRef=firebaseDatabase.getReference("forum");
        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

        myRef.push().setValue(new Chat(gameId,userId,poruka,""));
    }

    private void getUserName(){
        DatabaseReference myRef=firebaseDatabase.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //kreiramo listu i posaljemo u viewmodel
                for (DataSnapshot oneGame:dataSnapshot.getChildren()){
                    //key value parovi
                    User user=oneGame.getValue(User.class);;
                    for(int i=0;i<forum.size();i++){
                        Chat chat=forum.get(i);
                        if(chat.getIdUser().equals(oneGame.getKey())){
                            forum.get(i).setUserName(user.getFirstName()+" "+user.getLastName());
                            break;
                        }
                    }
                }
                fbViewModel.setForum(forum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO log i ispis korsniku
                Log.d(log_tag,"Greska kod dohvatanja imena korisnika");
            }
        });
    }

    public void getForum(String gameId){
        DatabaseReference myRef=firebaseDatabase.getReference("forum");
        myRef.orderByChild("idGame").equalTo(gameId);
        myRef.orderByChild("timestamp");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //kreiramo listu i posaljemo u viewmodel
                forum.clear();
                for (DataSnapshot oneGame:dataSnapshot.getChildren()){
                    //key value parovi
                    String id=oneGame.getKey();
                    Chat chat=oneGame.getValue(Chat.class);
                    chat.setId(id);
                    forum.add(chat);
                }
                //fbViewModel.setForum(forum);
                getUserName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //TODO log i ispis korsniku
                Log.d(log_tag,"Greska kod dohvatanja poruke");
            }
        });
    }
}
