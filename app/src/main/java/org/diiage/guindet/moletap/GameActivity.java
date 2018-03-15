package org.diiage.guindet.moletap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends AppCompatActivity {

    boolean isTimerRunning;
    Timer timer;
    int elapsedTime;
    Random rand =  new Random();
    ArrayList<ImageButton> lstMole = new ArrayList<>();

    int score = 0;

    int nextMole = 0;
    int currentMole = 0;

    TextView myScore;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timer = new Timer();

        elapsedTime = 0;

        lstMole.add((ImageButton)findViewById(R.id.mole1));
        lstMole.add((ImageButton)findViewById(R.id.mole2));
        lstMole.add((ImageButton)findViewById(R.id.mole3));
        lstMole.add((ImageButton)findViewById(R.id.mole4));
        lstMole.add((ImageButton)findViewById(R.id.mole5));
        lstMole.add((ImageButton)findViewById(R.id.mole6));
        lstMole.add((ImageButton)findViewById(R.id.mole7));
        lstMole.add((ImageButton)findViewById(R.id.mole8));
        lstMole.add((ImageButton)findViewById(R.id.mole9));

        for (final ImageButton item : lstMole)
        {
            item.setImageDrawable(null);
            item.setEnabled(false);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(item.isEnabled())
                    {
                        score++;
                        item.setEnabled(false);
                        item.setImageDrawable(null);
                    }
                }
            });
        }

        startTimer();

    }

    protected void startTimer() {
        isTimerRunning = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run()
            {
                elapsedTime += 1;
                mHandler.obtainMessage(1).sendToTarget();
            }
        }, 0, 10);
    }

    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            int tmp = 3000-elapsedTime;

            if (elapsedTime%100 == 0)
            {
                ((TextView)findViewById(R.id.txtChrono)).setText(String.valueOf(tmp/100));
            }

            if (elapsedTime > nextMole)
            {
                //On enleve l'ancienne taupe
                lstMole.get(currentMole).setEnabled(false);
                lstMole.get(currentMole).setImageDrawable(null);

                currentMole = rand.nextInt((8 - 0) + 1) + 0;
                lstMole.get(currentMole).setEnabled(true);
                lstMole.get(currentMole).setImageResource(R.drawable.lilmole);

                nextMole += rand.nextInt((150 - 50) + 1) + 50;
            }

            if (tmp == 0)
            {
                timer.cancel();

                for (final ImageButton item : lstMole)
                {
                    item.setImageResource(R.drawable.lilmole);
                    item.setEnabled(false);
                }

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("score", score);
                intent.putExtras(bundle);

                setResult(0,intent);
                finish();
            }
        }
    };
}
