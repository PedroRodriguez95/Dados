package Model;

import java.util.ArrayList;

import Interfaces.IThrowScoreCalculator;
import Interfaces.IThrowable;

public class ScoreCalculator implements IThrowScoreCalculator {
    private IThrowScoreCalculator[] calculators;

    public ScoreCalculator(IThrowScoreCalculator[] calculators) {
        this.calculators = calculators;
    }
    
    @Override
    public ScoreCalculation calculateScore(ArrayList<IThrowable> throwables) {
		int tempScore = 0;
		ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();

		for (IThrowScoreCalculator c : this.calculators) {
			ScoreCalculation calculation = c.calculateScore(throwables);
			tempScore += calculation.getScore();
			tempThrowables.addAll(calculation.getThrowables());
		}

		return new ScoreCalculation(tempScore, tempThrowables);
	}
}
