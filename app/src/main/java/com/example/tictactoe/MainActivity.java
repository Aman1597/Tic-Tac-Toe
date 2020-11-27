package com.example.tictactoe;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //    Player Representation
    //    0 = X
    //    1 = O
    String s;
    boolean gameActive = true;
    int activePlayer = 0;
    int tappedImage;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    /*  State Meaning:
        0 - X
        1 - O
        2 - Null            */
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                        {0,3,6}, {1,4,7}, {2,5,8},
                        {0,4,8}, {2,4,6}};

    public void playerTap(View view)
    {
        if(!gameActive)
        {
            gameReset();
        }
        else
        {
            ImageView img = (ImageView) view;
            tappedImage = Integer.parseInt(img.getTag().toString());
            if (gameState[tappedImage] == 2)
            {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0)
                {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("Player2's Turn-Tap to play");
                }
                else
                {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("Player1's Turn-Tap to play");
                }
                img.animate().translationYBy(1000f).setDuration(150);
            }

            // Check for WIN

            for (int[] winPosition : winPositions)
            {
                if ((gameState[winPosition[0]] == gameState[winPosition[1]]) &&
                        (gameState[winPosition[1]] == gameState[winPosition[2]]) &&
                        (gameState[winPosition[0]] != 2))
                {
                    // Somebody has Won! - Find out who!
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0)
                    {
                        s = "Player1 has won";
                    }
                    else
                    {
                        s = "Player2 has won";
                    }
                    // Update the status bar for winner announcement
                    alertBox(s);
                }
            }

            if(gameActive)
            {
                if((gameState[0] != 2)&&(gameState[1] != 2)&&(gameState[2] != 2)&&
                    (gameState[3] != 2)&&(gameState[4] != 2)&&(gameState[5] != 2)&&
                        (gameState[6] != 2)&&(gameState[7] != 2)&&(gameState[8] != 2))
                {
                    gameActive = false;
                    alertBox("Match Tied");
                }
            }
        }
    }

    public void gameReset(){
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Player1's Turn-Tap to play");
    }

    public void alertBox(String s1){
        AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
        a.setMessage(s1);
        a.setTitle("Match Result");
        a.setCancelable(false);
        a.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameReset();
            }
        });
        a.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog ad = a.create();
        ad.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
