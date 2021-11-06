package Model;

import java.util.ArrayList;


import Interfaces.IThrowable;
import Interfaces.IThrowScoreCalculator;

public class VerifierThreeDices implements IThrowScoreCalculator {

    @Override
    public ScoreCalculation calculateScore(ArrayList<IThrowable> throwables) {
        ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
        int score = 0;
        for (int j = 0; j <= throwables.size() - 3;) {
            int tempScore = verifyThreeEquals(throwables.get(j), throwables.get(j + 1), throwables.get(j + 2));
            if (tempScore > 0) {
                score += tempScore;
                tempThrowables.add(throwables.get(j + 2));
                tempThrowables.add(throwables.get(j + 1));
                tempThrowables.add(throwables.get(j));
                throwables.remove(j);
                throwables.remove(j);
                throwables.remove(j);
            } else {
                j++;
            }
        }
        return new ScoreCalculation(score, tempThrowables); 
    }

    private int verifyThreeEquals(IThrowable dice1, IThrowable dice2, IThrowable dice3) {
        if (dice1.getValue() == dice2.getValue() && dice1.getValue() == dice3.getValue()) {
            if (dice1.getValue() == 1) {
                return 1000;
            }
            return dice1.getValue() * 100;
        }
        return 0;
    }
    
}
