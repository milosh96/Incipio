package com.wordpress.qubiplatform.incipio.util;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.activity.GameActivity;
import com.wordpress.qubiplatform.incipio.firebase.entity.Game;

import java.util.ArrayList;
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
    }
}
