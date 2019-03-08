package com.wordpress.qubiplatform.incipio.util;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.activity.GameActivity;
import com.wordpress.qubiplatform.incipio.firebase.entity.Game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.GameHolder> {

    private List<Game> games=new ArrayList<>();

    @Override
    public GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View created=LayoutInflater.from(parent.getContext()).inflate(R.layout.game_element,parent,false);
        return new GameHolder(created);
    }

    @Override
    public void onBindViewHolder(GameHolder holder, int position) {
        Game game=games.get(position);
        holder.setChannel(game.getChannel());
        holder.setDesc(game.getDescription());
        holder.setStatus(game.getStatus());
        holder.setTime(game.getStartTime(), game.getEndTime());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(List<Game> games){
        this.games=games;

        //TODO optimizacija
        notifyDataSetChanged();
    }

    class GameHolder extends RecyclerView.ViewHolder{

        private ImageView mChannel;
        private TextView mDesc;
        private ImageView mChannelPadd;
        private LinearLayout mLayout;

        //vers 2
        private ProgressBar progressBar;
        private TextView startTime;
        private TextView endTime;

        public GameHolder(final View itemView) {
            super(itemView);

            //add on click
            ImageView enter=itemView.findViewById(R.id.game_enter_image);
            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dohvati gde je kliknuto i kreiraj intent TODO
                    int position=getAdapterPosition();
                    Game game=games.get(position);
                    String gameId=game.getId();

                    //Intent za game details
                    Intent intent=new Intent(itemView.getContext(),GameActivity.class);
                    intent.putExtra("GAME_ID", gameId);
                    itemView.getContext().startActivity(intent);
                }
            });

            mChannel=itemView.findViewById(R.id.channel_image);
            mDesc=itemView.findViewById(R.id.game_description);
            mChannelPadd=itemView.findViewById(R.id.channel_image_padding);
            mLayout=itemView.findViewById(R.id.game_bar);

            progressBar=itemView.findViewById(R.id.progressTime);
            startTime=itemView.findViewById(R.id.startTime);
            endTime=itemView.findViewById(R.id.endTime);
        }

        public void setChannel(int channel){

            //dodati jos kanala po potrebi
            switch (channel){
                case 1:{
                    mChannel.setImageResource(R.drawable.sk1);
                    break;
                }
                case 2:{
                    mChannel.setImageResource(R.drawable.sk2);
                    break;
                }
                case 3:{
                    mChannel.setImageResource(R.drawable.sk3);
                    break;
                }
                default:{
                    mChannel.setImageResource(R.drawable.skhd);
                    break;
                }
            }
        }

        public void setDesc(String desc){
            mDesc.setText(desc);
        }
        public void setStatus(String status){
            //TODO
            /**
             * ako je status !=ready
             * setuj grey za layout
             * mChannel
             * i mPadd
             */
        }

        public void setTime(String startTime, String endTime){
            this.startTime.setText(startTime);
            this.endTime.setText(endTime);

            String[] starts=startTime.split(":");
            String[] ends=endTime.split(":");

            String startTimeHour=starts[0];
            String startTimeMin=starts[1];
            String endTimeHour=ends[0];
            String endTimeMin=ends[1];



            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            int minutes=rightNow.get(Calendar.MINUTE);

            int startHour=0;
            int startMinute=0;
            int endHour=0;
            int endMinute=0;

            try{
                startHour=Integer.parseInt(startTimeHour);
                startMinute=Integer.parseInt(startTimeMin);

                endHour=Integer.parseInt(endTimeHour);
                endMinute=Integer.parseInt(endTimeMin);

            }
            catch (Exception e){
                Log.d("ERROR","Greska kod parsiranja u int");
                e.printStackTrace();
            }

            //racunanje procenta
            int totalStart=startHour*60+startMinute;
            int totalEnd=endHour*60+endMinute;
            int totalCurr=hour*60+minutes;

            if(totalCurr>=totalEnd){
                //zavrsio se
                progressBar.setProgress(100);
            }
            else if(totalCurr<=totalStart){
                progressBar.setProgress(0);
            }
            else{
                int diff=totalEnd-totalStart;
                int curr=totalCurr-totalStart;
                int progress=100*curr/diff;
                progressBar.setProgress(progress);
            }
        }
    }
}
