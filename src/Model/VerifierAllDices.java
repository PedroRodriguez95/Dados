package Model;

import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowableVerifier;

public class VerifierAllDices implements IThrowableVerifier {

    @Override
    public int verify(ArrayList<IThrowable> throwables) {
        throwables.sort(new ThrowableComparator());
        int score = 0;
        for (int j = 0; j <= throwables.size() - 5;) {
            int tempScore = verifyFiveEquals(throwables.get(j), throwables.get(j + 1), throwables.get(j + 2), throwables.get(j + 3), throwables.get(j + 4));
            if (tempScore > 0) {
                score += tempScore;
                throwables.clear();
            } else {
                j++;
            }
        }
        return score;

    }
    //TODO: simplificar para que el metodo reciba un array y no los dados individuales.
    private int verifyFiveEquals(IThrowable dice1, IThrowable dice2, IThrowable dice3, IThrowable dice4, IThrowable dice5) {
        if (dice1.getValue() == dice2.getValue() && dice1.getValue() == dice3.getValue() && dice1.getValue() == dice4.getValue() && dice1.getValue() == dice5.getValue()) {
            if (dice1.getValue() == 1) {
                return 10000;
            }
            return dice1.getValue() * 1000;
        }
        return 0;
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
