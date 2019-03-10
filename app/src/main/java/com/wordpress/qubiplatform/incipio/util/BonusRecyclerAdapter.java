package com.wordpress.qubiplatform.incipio.util;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.qubiplatform.incipio.R;
import com.wordpress.qubiplatform.incipio.activity.quiz.QuizActivity;
import com.wordpress.qubiplatform.incipio.activity.quiz.RatingActivity;
import com.wordpress.qubiplatform.incipio.activity.quiz.SimpleActivity;
import com.wordpress.qubiplatform.incipio.firebase.entity.Quiz;

import java.util.ArrayList;
import java.util.List;

public class BonusRecyclerAdapter extends RecyclerView.Adapter<BonusRecyclerAdapter.QuizHolder> {

    private List<Quiz> quizzes=new ArrayList<>();

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_element,parent,false);
        return new QuizHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        Quiz quiz=quizzes.get(position);
        holder.setFields(quiz);
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public void setQuiz(List<Quiz> quizzes){
        //TODO optimization

        this.quizzes=quizzes;
        notifyDataSetChanged();
    }

    class QuizHolder extends RecyclerView.ViewHolder{

        private View leftBanner;
        private View rightBanner;
        private TextView description;
        private TextView order;
        private String quizId="";
        private String type="";

        public QuizHolder(View itemView) {

            super(itemView);
            leftBanner=itemView.findViewById(R.id.quiz_start);
            rightBanner=itemView.findViewById(R.id.quiz_end);
            order=itemView.findViewById(R.id.quiz_order);
            description=itemView.findViewById(R.id.quiz_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO define all types!
                    //sve opcije mogu sa ili bezslike/ ne treba zaseban activity
                    switch (type){
                        case "rating":{
                            //commentaators and stars to select
                            Intent intent=new Intent(v.getContext(),RatingActivity.class);
                            intent.putExtra("QUIZ_ID",quizId);
                            v.getContext().startActivity(intent);
                            break;
                        }
                        case "simple":{
                            //just text field for input
                            Intent intent=new Intent(v.getContext(),SimpleActivity.class);
                            intent.putExtra("QUIZ_ID",quizId);
                            v.getContext().startActivity(intent);
                            break;
                        }
                        case "given":{
                            //sa ponudjenim odgovorima
                            Intent intent=new Intent(v.getContext(),QuizActivity.class);
                            intent.putExtra("QUIZ_ID",quizId);
                            v.getContext().startActivity(intent);
                            break;
                        }
                    }

                }
            });

        }

        public void setFields(Quiz quiz){
            String color=quiz.getColor();
            if("green".equals(color)){
                leftBanner.setBackgroundResource(R.color.colorGreen);
                rightBanner.setBackgroundResource(R.color.colorGreen);
            }
            else{
                leftBanner.setBackgroundResource(R.color.colorRed);
                rightBanner.setBackgroundResource(R.color.colorRed);
            }

            int position=getAdapterPosition()+1;
            order.setText(position+".");
            description.setText(quiz.getQuestion());
            quizId=quiz.getId();
            type=quiz.getType();

        }
    }
}
