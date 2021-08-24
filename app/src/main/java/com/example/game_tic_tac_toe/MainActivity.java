package com.example.game_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setCancelable(false)
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Yes Exit", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //jwenfejrn
                    }
                });
        builder.show();

    }

    //0=Blue, 1=red
    int activePlayer = 0;
    boolean gameIsActive = true;

    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
//System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
        }
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.blackcrossss);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.redcircle);
            activePlayer = 0;
        }

        counter.animate()
                .translationYBy(1000f)
                .rotation(360)
                .setDuration(300);
        for (int[] winningPositions : winningPositions) {
            if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                    gameState[winningPositions[0]] != 2) {

                //Someone has Won
                gameIsActive = false;
                String winner = "red";
                if (gameState[winningPositions[0]] == 0) {
                    winner = "black";
                }


                TextView winnerMessage = (TextView) findViewById(R.id.WinnerMessage);
                winnerMessage.setText(winner + " has won");
                LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgain_Layout);
                layout.setVisibility(View.VISIBLE);

            } else {
                boolean gameIsOver = true;
                for (int counterState : gameState) {
                    if (counterState == 2) gameIsOver = false;
                }
                if (gameIsOver) {

                    TextView winnerMessage = (TextView) findViewById(R.id.WinnerMessage);

                    winnerMessage.setText(" It's a draw");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgain_Layout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgain_Layout);
        layout.setVisibility(View.INVISIBLE);
        gameIsActive = true;
        //0=Blue, 1=red
        activePlayer = 0;
        //2 means unplayed
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.Grid_Layout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}