package com.example.cc10624rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String playerVal = "", compVal = "";

    int playerPoint = 0, compPoint = 0, playerIDX = 0, compIDX = 0;
    String [] possibleVal = {"rock", "paper", "scissors"};
    Random rand = new Random();



    Button btn_rock, btn_paper, btn_scissors, btn_start, btn_reset;
    ImageView img_human, img_computer;
    TextView txt_scoreHuman, txt_scoreComp, txt_winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_rock = (Button) findViewById(R.id.btn_rock);
        btn_paper = (Button) findViewById(R.id.btn_paper);
        btn_scissors = (Button) findViewById(R.id.btn_scissors);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        img_human = (ImageView) findViewById(R.id.img_human);
        img_computer = (ImageView) findViewById(R.id.img_computer);
        txt_scoreComp = (TextView) findViewById(R.id.txt_scoreComp);
        txt_scoreHuman = (TextView) findViewById(R.id.txt_scoreHuman);
        txt_winner = (TextView) findViewById(R.id.txt_winner);

        img_computer.setImageResource(getResources().getIdentifier("draw"+0, "drawable", getPackageName()));
        img_human.setImageResource(getResources().getIdentifier("draw"+0, "drawable", getPackageName()));
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator humanDraw = ObjectAnimator.ofFloat(img_human, "rotation", 0f, 0f);
                ObjectAnimator compDraw = ObjectAnimator.ofFloat(img_computer, "rotation", 0f, 0f);
                humanDraw.setDuration(500).start();
                compDraw.setDuration(500).start();
                img_computer.setImageResource(getResources().getIdentifier("draw"+0, "drawable", getPackageName()));
                img_human.setImageResource(getResources().getIdentifier("draw"+0, "drawable", getPackageName()));
                playerPoint = 0;
                compPoint = 0;
                playerIDX = 0;
                compIDX = 0;
                txt_scoreComp.setText(String.valueOf(0));
                txt_scoreHuman.setText(String.valueOf(0));
                btn_reset.setVisibility(View.GONE);
                btn_start.setEnabled(true);
                txt_winner.setText("");

            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compIDX = rand.nextInt(3);
                compVal = possibleVal[compIDX];
                img_computer.setImageResource(getResources().getIdentifier("draw"+0, "drawable", getPackageName()));
                img_human.setImageResource(getResources().getIdentifier("draw"+0, "drawable", getPackageName()));
                animateStart();
                compareVal(playerVal, compVal);

            }
        });

        btn_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerVal = "rock";
                playerIDX = 0;
                img_human.setImageResource(getResources().getIdentifier("draw"+0, "drawable", getPackageName()));
            }
        });

        btn_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerVal = "paper";
                playerIDX = 1;
                img_human.setImageResource(getResources().getIdentifier("draw"+1, "drawable", getPackageName()));
            }
        });

        btn_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerVal = "scissors";
                playerIDX = 2;
                img_human.setImageResource(getResources().getIdentifier("draw"+2, "drawable", getPackageName()));
            }
        });
    }
    public void compareVal(String playerVal, String compVal){
        if (playerVal == "rock" && compVal == "paper") {
            ++this.compPoint;
        } else if (playerVal == "paper" && compVal == "rock"){
            ++this.playerPoint;
        }

        if (playerVal == "scissors" && compVal == "rock") {
            ++this.compPoint;
        } else if (playerVal == "rock" && compVal == "scissors"){
            ++this.playerPoint;
        }

        if (playerVal == "scissors" && compVal == "paper") {
            ++this.playerPoint;
        } else if (playerVal == "paper" && compVal == "scissors"){
            ++this.compPoint;
        }
    }
    final Handler handler = new Handler();
    private void animateStart()
    {
        ObjectAnimator animPlayer = ObjectAnimator.ofFloat(img_human, "rotation", 0f, 50f);
        ObjectAnimator animComp = ObjectAnimator.ofFloat(img_computer, "rotation", 0f, -50f);
        animPlayer.setDuration(1000).start();
        animComp.setDuration(1000).start();
        handler.postDelayed(delayedStartDraw,1000);
        handler.postDelayed(delayedEndDraw,1500);
        handler.postDelayed(delayedDraw, 1600);
    }
    Runnable delayedStartDraw = new Runnable() {
        public void run() {
            ObjectAnimator humanDraw = ObjectAnimator.ofFloat(img_human, "rotation", 0f, -50f);
            ObjectAnimator compDraw = ObjectAnimator.ofFloat(img_computer, "rotation", 0f, 50f);
            humanDraw.setDuration(500).start();
            compDraw.setDuration(500).start();
        }
    };
    Runnable delayedEndDraw = new Runnable() {
        public void run() {
            ObjectAnimator humanDraw = ObjectAnimator.ofFloat(img_human, "rotation", 0f, 90f);
            ObjectAnimator compDraw = ObjectAnimator.ofFloat(img_computer, "rotation", 0f, -90f);
            humanDraw.setDuration(100).start();
            compDraw.setDuration(100).start();
        }
    };

    Runnable delayedDraw = new Runnable() {
        @Override
        public void run() {
            img_computer.setImageResource(getResources().getIdentifier("draw"+compIDX, "drawable", getPackageName()));
            img_human.setImageResource(getResources().getIdentifier("draw"+playerIDX, "drawable", getPackageName()));
            txt_scoreComp.setText(String.valueOf(compPoint));
            txt_scoreHuman.setText(String.valueOf(playerPoint));
            if (playerPoint == 5){
                btn_reset.setVisibility(View.VISIBLE);
                btn_start.setEnabled(false);
                txt_winner.setText("PLAYER WINS!");

            } else if (compPoint == 5) {
                btn_reset.setVisibility(View.VISIBLE);
                btn_start.setEnabled(false);
                txt_winner.setText("COMPUTER WINS!");
            }
        }
    };
}