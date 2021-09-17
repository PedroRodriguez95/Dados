package Model;

import java.util.ArrayList;


import Interfaces.IThrowable;
import Interfaces.IThrowableVerifier;

public class VerifierThreeDices implements IThrowableVerifier {

    @Override
    public int verify(ArrayList<IThrowable> throwables) {
        int score = 0;
        for (int j = 0; j <= throwables.size() - 3;) {
            int tempScore = verifyThreeEquals(throwables.get(j), throwables.get(j + 1), throwables.get(j + 2));
            if (tempScore > 0) {
                score += tempScore;
                throwables.remove(j + 2);
                throwables.remove(j + 1);
                throwables.remove(j);
            } else {
                j++;
            }
        }
        return score;

        
        
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
