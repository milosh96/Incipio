package com.wordpress.qubiplatform.incipio.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wordpress.qubiplatform.incipio.R;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);


        Toolbar toolbar = findViewById(R.id.game_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                openHome();
            }
        }).start();
    }

    public void openHome(){
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
