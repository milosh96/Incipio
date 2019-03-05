package com.wordpress.qubiplatform.incipio.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        public ChatHolder(View itemView) {
            super(itemView);

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
