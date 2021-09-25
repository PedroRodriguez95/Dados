package Model;

import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowScoreCalculator;

public class VerifierFourDices implements IThrowScoreCalculator {

    @Override
    public ScoreCalculation calculateScore(ArrayList<IThrowable> throwables) {
        ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
        int score = 0;
        for (int j = 0; j <= throwables.size() - 4;) {
            int tempScore = verifyFourEquals(throwables.get(j), throwables.get(j + 1), throwables.get(j + 2),throwables.get(j + 3));
            if (tempScore > 0) {
                score += tempScore;
                tempThrowables.add(throwables.get(j + 3));
                tempThrowables.add(throwables.get(j + 2));
                tempThrowables.add(throwables.get(j + 1));
                tempThrowables.add(throwables.get(j));
            } else {
                j++;
            }
        }
        return new ScoreCalculation(score, tempThrowables);

    }

    private int verifyFourEquals(IThrowable dice1, IThrowable dice2, IThrowable dice3, IThrowable dice4) {
        if (dice1.getValue() == dice2.getValue() && dice1.getValue() == dice3.getValue() && dice1.getValue() == dice4.getValue()) {
            if (dice1.getValue() == 1) {
                return 5000;
            }
            return dice1.getValue() * 500;
        }
        return 0;
    }

    
}
