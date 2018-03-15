package model;

import java.io.Serializable;

/**
 * Created by nguindet on 15/03/2018.
 */

public class Score implements Serializable
{
    String name;
    int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
