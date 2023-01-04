package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 player is cross, 1 is circle
    int activePlayer = 0;

    // 2 means imageview is empty
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isgameActive= true;
    int move_counter=0;

    public void DisplayImage(View view)
    {
        ImageView counter= (ImageView) view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter] == 2 && isgameActive) {
            gameState[tappedCounter]=activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.circle);
            }

            counter.animate().rotation(360).setDuration(500);
            move_counter++;

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "Circle";
                    } else {
                        winner = "Cross";
                    }

                    isgameActive=false;

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won");
                    Button playagainbtn = (Button) findViewById(R.id.playagainbtn);
                    playagainbtn.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
                // if game is draw
                else if(move_counter==9 && isgameActive==true){
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText("Match Draw");
                    Button playagainbtn = (Button) findViewById(R.id.playagainbtn);
                    playagainbtn.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view)
    {
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button playagainbtn = (Button) findViewById(R.id.playagainbtn);
        playagainbtn.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        activePlayer=0;
        isgameActive=true;
        move_counter=0;

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        int[] allImages= {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,
                R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9};

        for(int i=0;i<allImages.length;i++){
            ImageView counter= (ImageView) findViewById(allImages[i]);
            counter.setImageDrawable(null);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView labelTextView= (TextView) findViewById(R.id.labelTextView);
        labelTextView.animate().alpha(1).setDuration(1500);

    }
}