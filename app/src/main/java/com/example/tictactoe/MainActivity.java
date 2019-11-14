package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for(int i = 0; i< 3; i++){
            for(int j = 0;j< 3;j++){
                String buttonID = "button_" + i + j; // reason why naming buttons 00, 01, 02, ...
                // return a Resource instance - a class accessing the app's resources
                int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
                // get ref to all btn using this code
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this); // passing MainActivity as onClickListener
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetGame();
            }
        });
    }


    // each time click, mark the btn with appropriate symbol of current playing side
    // time check if any side is eligible for winning or not, else the game is draw
    // then lastly change the turn to the other side (when the previous side done making his move)
    @Override
    public void onClick(View v) { // this func is passed to buttons[i][j].setOnClickListener(this)
        if(!((Button) v).getText().toString().equals("")){ // checks if this btn that was clicked contains an empty str
            // if not the case -> btn already used (X or O on the btn
            return;
        }
        if(player1Turn){
            ((Button) v).setText("X");

        }else {
            ((Button)v).setText("O");
        }
        roundCount++;
        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9){
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();

            }
        }

        for(int i = 0; i < 3 ; i++){ // for row
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                // game logic to win
                return true;
            }
        }

        for(int i = 0; i < 3 ; i++){ // for column
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                // game logic to win
                return true;
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){ // for cross left->right
            // game logic to win
            return true;
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){ // for cross righht->left
            // game logic to win
            return true;
        }

        return false;
    }

    private void player1Wins(){
        player1Points++;
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins(){
        player2Points++;
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void updatePointsText(){
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard(){
        for (int i = 0; i< 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Points",player1Points);
        outState.putInt("player2Points",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }
}
