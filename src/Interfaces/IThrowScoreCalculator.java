package Interfaces;

import java.util.ArrayList;

import Model.ScoreCalculation;

public interface IThrowScoreCalculator {
    public ScoreCalculation calculateScore(ArrayList<IThrowable> throwables);
}
