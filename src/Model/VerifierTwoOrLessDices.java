package Model;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowableVerifier;

public class VerifierTwoOrLessDices implements IThrowableVerifier{

    @Override
    public int verify(ArrayList<IThrowable> throwables) {
        int score = 0;
        for (IThrowable t: throwables){
            if (t.getValue() == 1){
                score += 100;
            }
            if (t.getValue() == 5){
                score += 50;
            }
        }
        return score;
    }
}