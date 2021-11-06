package Model;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowScoreCalculator;

public class VerifierTwoOrLessDices implements IThrowScoreCalculator{

    @Override
    public ScoreCalculation calculateScore(ArrayList<IThrowable> throwables) {
        ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
        int score = 0;
        for (int i = 0; i <= throwables.size() - 1 ; i++) {
            int tempScore = this.calculateScore(throwables.get(i).getValue());
            if (tempScore > 0) {
                score += tempScore;
                tempThrowables.add(throwables.get(i));
            }
        }
        return new ScoreCalculation(score, tempThrowables);
    }

    private int calculateScore(int value) {
        if (value == 1) {
            return 100; 
        } else if (value == 5) {
            return 50;
        }
        return 0;
    }
}