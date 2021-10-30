package Model;

import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowScoreCalculator;

public class VerifierAllDices implements IThrowScoreCalculator {

    @Override
    public ScoreCalculation calculateScore(ArrayList<IThrowable> throwables) {
        ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
        throwables.sort(new ThrowableComparator());
        int score = 0;
        for (int j = 0; j <= throwables.size() - 5;) {
            int tempScore = verifyFiveEquals(throwables) + verifyStraight(throwables);
            if (tempScore > 0) {
                score += tempScore;
                tempThrowables.addAll(throwables);
                throwables.clear();
            } else {
                j++;
            }
        }
        return new ScoreCalculation(score, tempThrowables);
    }

    private int verifyFiveEquals(ArrayList<IThrowable> throwables) {

        int expectedValue = throwables.get(0).getValue();
        for (int i = 1; i < throwables.size(); i++) {
            if (throwables.get(i).getValue() != expectedValue) {
                return 0;
            }
        }

        if (expectedValue == 1) {
            return 10000;
        } 
        
        return 1000 * expectedValue;
    }



    private int verifyStraight(ArrayList<IThrowable> throwables) {
        for (int i = 1; i < throwables.size(); i++){
            if (!(throwables.get(i).getValue() == throwables.get(i - 1).getValue() + 1)) {
                return 0;
            }
        }
        return 5000;
    }

    
}
