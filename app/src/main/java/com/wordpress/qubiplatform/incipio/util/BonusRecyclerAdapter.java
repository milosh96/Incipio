package com.wordpress.qubiplatform.incipio.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class BonusRecyclerAdapter extends RecyclerView.Adapter<BonusRecyclerAdapter.QuizHolder> {

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class QuizHolder extends RecyclerView.ViewHolder{

        public QuizHolder(View itemView) {
            super(itemView);
        }
    }
}
