package Model;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowableVerifier;

public class VerifierTwoOrLessDices implements IThrowableVerifier{

    @Override
    public int verify(ArrayList<IThrowable> throwables) {
        int score = 0;
        for (int i = 0; i <= throwables.size() - 2 ; i++) {
            int tempScore = this.calculateScore(throwables.get(i).getValue());
            if (tempScore > 0) {
                score += tempScore;
                throwables.remove(i);
                i--;
            }
        }
        return score;
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