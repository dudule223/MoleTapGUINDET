package org.diiage.guindet.moletap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Score;

public class ScoresActivity extends AppCompatActivity {

    private static class ScoreViewOlder
    {
        TextView name ;
        TextView score;

        public ScoreViewOlder(TextView name, TextView score) {
            this.name = name;
            this.score = score;
        }
    }

    private static class ScoreAdapter extends BaseAdapter {

        ArrayList<Score> scores;
        ScoresActivity context;

        public ScoreAdapter(ScoresActivity context, ArrayList<Score> scores)
        {
            this.scores = scores;
            this.context = context;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return scores.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view;
            ScoreViewOlder scoreViewOlder;

            if (convertView != null)
            {
                view = convertView;
                scoreViewOlder = (ScoreViewOlder) view.getTag();
            }
            else
            {
                LayoutInflater layoutInflater = context.getLayoutInflater();
                view = layoutInflater.inflate(R.layout.score_layout,parent,false);

                TextView name = view.findViewById(R.id.lblName);
                TextView score = view.findViewById(R.id.lblScore);

                scoreViewOlder = new ScoreViewOlder(name,score);
            }

            view.setTag(scoreViewOlder);

            Score score = (Score) getItem(position);

            scoreViewOlder.name.setText(score.getName());
            scoreViewOlder.score.setText(score.getScore());

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Bundle extra = getIntent().getExtras();

        if (extra != null)
        {
            final ArrayList<Score> scores = (ArrayList<Score>) extra.getSerializable("scores");

            ListView lst = findViewById(R.id.lstScores);

            ArrayAdapter<Score> adapter = new ArrayAdapter<Score>(ScoresActivity.this,android.R.layout.simple_list_item_1,scores);

            //ScoreAdapter adapter = new ScoreAdapter(ScoresActivity.this, scores);


            lst.setAdapter(adapter);


        }
    }
}
