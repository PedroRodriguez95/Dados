package Model;

import java.util.ArrayList;

import Interfaces.IThrowable;

public class ScoreCalculation {

    private int score;
    private ArrayList<IThrowable> throwables;

    public ScoreCalculation(int score, ArrayList<IThrowable> throwables) {
        this.score = score;
        this.throwables = throwables;
    }

    public int getScore() {
        return this.score;
    }

    public ArrayList<IThrowable> getThrowables() {
        return this.throwables;
    }
    
}
