package org.diiage.guindet.moletap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import model.Score;

public class MainActivity extends AppCompatActivity {

    ArrayList<Score> lstScores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewGame = findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText txtName = findViewById(R.id.txtName);
                if (txtName.getText().toString() != "")
                {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("username", txtName.getText().toString());
                    intent.putExtras(bundle);

                    startActivityForResult(intent,0);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Username field empty",Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btnScores = findViewById(R.id.btnScores);
        btnScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!lstScores.isEmpty())
                {
                    Intent intent = new Intent(MainActivity.this, ScoresActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("scores", lstScores);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"No score",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Check which request we're responding to
        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                Bundle extra = data.getExtras();
                Score score = new Score(((EditText)findViewById(R.id.txtName)).getText().toString(), extra.getInt("score"));
                lstScores.add(score);


                Toast.makeText(MainActivity.this,"Score : "+score.getScore(),Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Partie abandonnée",Toast.LENGTH_LONG).show();
            }
        }
    }
}
