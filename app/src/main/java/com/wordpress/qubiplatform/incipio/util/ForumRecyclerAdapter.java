package com.wordpress.qubiplatform.incipio.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.firebase.entity.Chat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ForumRecyclerAdapter extends RecyclerView.Adapter<ForumRecyclerAdapter.ChatHolder> {

    private List<Chat> forum=new ArrayList<>();

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_element,parent,false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Chat chat=forum.get(position);
        holder.setFields(chat);
    }

    @Override
    public int getItemCount() {
        return forum.size();
    }

    public void setForum(List<Chat> forum){
        //TODO better optimization
        this.forum=forum;
        notifyDataSetChanged();
    }

    public class ChatHolder extends RecyclerView.ViewHolder{

        private TextView chat_owner;
        private TextView likes;
        private TextView dislikes;
        private TextView body;
        private ImageView options;
        private Context context;

        public ChatHolder(View itemView) {
            super(itemView);

            context=itemView.getContext();

            chat_owner=itemView.findViewById(R.id.chat_owner);
            likes=itemView.findViewById(R.id.chat_likes);
            dislikes=itemView.findViewById(R.id.chat_dislikes);
            body=itemView.findViewById(R.id.chat_body);
            options=itemView.findViewById(R.id.chat_options);

            options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO opcije sa
                    /**
                     * reply to
                     * like
                     * dislike
                     * view profile
                     */

                    PopupMenu menu=new PopupMenu(context,options);
                    menu.inflate(R.menu.options);
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.options_like:{
                                    Toast.makeText(context,"Opcija like",Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case R.id.options_dislike:{
                                    Toast.makeText(context,"Opcija dislike",Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case R.id.options_reply:{
                                    Toast.makeText(context,"Opcija reply",Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case R.id.options_profile:{
                                    Toast.makeText(context,"Opcija profil",Toast.LENGTH_LONG).show();
                                    break;
                                }
                                default:{
                                    Toast.makeText(context,"Opcija default",Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }
                            return false;
                        }
                    });
                    menu.show();
                }
            });
        }

        public void setFields(Chat chat){
            chat_owner.setText(chat.getUserName());
            likes.setText(chat.getLikes()+"");
            dislikes.setText(chat.getDislikes()+"");
            body.setText(chat.getBody());
        }
    }
}
