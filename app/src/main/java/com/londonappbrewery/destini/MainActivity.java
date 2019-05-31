package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mStoryTextView;
    Button mAnswerTop;
    Button mAnswerBottom;
    // TODO: Declare as variaveis aqui:
    //indice corrente da historia
    //private Story mStorySelected;
    Story T1 = new Story(R.string.T1_Story);
    Story T2 = new Story(R.string.T2_Story);
    Story T3 = new Story(R.string.T3_Story);
    Story T4 = new Story(R.string.T4_End);
    Story T5 = new Story(R.string.T5_End);
    Story T6 = new Story(R.string.T6_End);

    Answer A1 = new Answer(R.string.T1_Ans1, T3);
    Answer A2 = new Answer(R.string.T1_Ans2, T2);
    Answer A3 = new Answer(R.string.T2_Ans1, T3);
    Answer A4 = new Answer(R.string.T2_Ans2, T4);
    Answer A5 = new Answer(R.string.T3_Ans1, T6);
    Answer A6 = new Answer(R.string.T3_Ans2, T5);

    private Story mStoryIndex = T1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: Faça o link do layout com a activity
        mStoryTextView = findViewById(R.id.storyTextView);
        mAnswerTop = findViewById(R.id.buttonTop);
        mAnswerBottom = findViewById(R.id.buttonBottom);

        if (savedInstanceState != null) {
            String message = savedInstanceState.getString("message");
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        //TODO:faça o mapeamento da história
        T1.setAnswerTop(A1);
        T1.setAnswerBottom(A2);
        T2.setAnswerTop(A3);
        T2.setAnswerBottom(A4);
        T3.setAnswerTop(A5);
        T3.setAnswerBottom(A6);
        mStoryTextView.setText(mStoryIndex.getStoryID());
        mAnswerTop.setText(mStoryIndex.getAnswerTop().getAnswerID());
        mAnswerBottom.setText(mStoryIndex.getAnswerBottom().getAnswerID());

        // TODO: Coloque o evento do click do botão, caso precise colocar a visibilidade no botão invisivel utilize a função
        mAnswerTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStoryTextView.setText(mStoryIndex.getStoryID());
                updateStory(mStoryIndex.getAnswerTop().getChildStory());
                if (mStoryIndex == T4 || mStoryIndex == T5 || mStoryIndex == T6) {
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStoryIndex.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStoryIndex.getAnswerBottom().getAnswerID());
                }

            }
        });

        mAnswerBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStoryTextView.setText(mStoryIndex.getStoryID());
                if (mStoryIndex == T4 || mStoryIndex == T5 || mStoryIndex == T6) {
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStoryIndex.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStoryIndex.getAnswerBottom().getAnswerID());
                }
            }
        });
    }
    public void updateStory(Story newStory){
        mStoryIndex = newStory;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("message", "This is my message to be reloaded");
        super.onSaveInstanceState(outState);
    }

}