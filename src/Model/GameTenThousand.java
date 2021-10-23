package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.IAction;
import Interfaces.IGame;
import Interfaces.IMenu;
import Interfaces.IThrowScoreCalculator;
import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;

public class GameTenThousand implements IGame, IThrowerListener {
	
	private ArrayList<IThrowable> allThrowables = new ArrayList<IThrowable>();
	private ArrayList<IThrowable> throwablesToThrow = new ArrayList<IThrowable>();
	private ArrayList<IThrowable> unthrowableThrowables = new ArrayList<IThrowable>();
	private ArrayList<Player> throwers = new ArrayList<Player>();
	private HashMap<IThrower,Integer> scores = new HashMap<IThrower,Integer>();
	private IThrowScoreCalculator scoreCalculator = new ScoreCalculator(new IThrowScoreCalculator[] { new VerifierAllDices(), new VerifierFourDices(), new VerifierThreeDices(), new VerifierTwoOrLessDices() });
	private ArrayList<ScoreCalculation> tempScoreCalculations = new ArrayList<ScoreCalculation>();
	private int currentTurn = 0;

	public GameTenThousand(int throwableAmount, int throwableFaces,int players){
		this.allThrowables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.throwers = new PlayerFactory().generatePlayers(players, new ThrowerFactory(), this);
		this.resetThrowables();
	}
	
	private void resetThrowables() {
		this.throwablesToThrow.clear();
		for (IThrowable throwable : this.allThrowables) {
			this.throwablesToThrow.add(throwable);
		}
	}

	@Override
	public void setUp() {
		for (IThrower t : this.throwers){
			this.scores.put(t, 0);
		}
		this.currentPlayerThrowThrowables();
	}

	@Override
	public void onThrowStart(IThrower thrower) {
		// Intencionalmente vacio
	}

	@Override
	public void onThrowStop(IThrower thrower) {
		ScoreCalculation tempCalculation = this.scoreCalculator.calculateScore(this.throwablesToThrow);
		if (tempCalculation.getScore() == 0) {
			this.nextTurn();
		} else {
			this.showAfterThrowMenu();
		}
	}

	public ArrayList<IThrowable> getThrowables() {
		return this.allThrowables;
	}

	private void nextTurn() {
		this.currentTurn++;
		if (this.currentTurn >= this.throwers.size()) {
			this.currentTurn = 0;
		}
		this.tempScoreCalculations.clear();
		this.resetThrowables();
		this.currentPlayerThrowThrowables();
	}

	private void storeScore() {
		int score = 0;
		for (ScoreCalculation s : this.tempScoreCalculations) {
			score += s.getScore();
		}
		IThrower thrower = this.throwers.get(this.currentTurn);
		this.scores.put(thrower, this.scores.get(thrower) + score);
		this.nextTurn();
	}

	private void selectThrowablesToThrow() {
		IMenu menu = new Menu();
		ArrayList<IAction> actions = new ArrayList<>();
		actions.add(this::toggleFirstThrowableSelected);
		actions.add(this::toggleSecondThrowableSelected);  
		actions.add(this::toggleThirdThrowableSelected); 
		actions.add(this::toggleFourthThrowableSelected); 
		actions.add(this::toggleFifthThrowableSelected);

		for (int i = 0; i < this.allThrowables.size(); i++) {
			IThrowable throwable = this.allThrowables.get(i);
			String throwableLabel = "" + (i + 1) + " (" + throwable.getValue() + ")";
			if (this.throwablesToThrow.contains(throwable)) {
				menu.addOption(new Option("Separar dado " + throwableLabel, actions.get(i)));
			} else if (!this.unthrowableThrowables.contains(throwable)){
				menu.addOption(new Option("Retornar dado " + throwableLabel, actions.get(i)));
			}
		}

		menu.addOption(new Option("Tirar", this::currentPlayerThrowThrowables));
		menu.addOption(new Option("Cancelar", this::showAfterThrowMenu));
	} 

	private void toggleThrowableSelected(int atIndex) {
		IThrowable throwable = this.allThrowables.get(atIndex);
		if (this.throwablesToThrow.contains(throwable)) {
			this.throwablesToThrow.remove(throwable);
		} else {
			this.throwablesToThrow.add(throwable);
		} 
		this.selectThrowablesToThrow();
	}

	private void toggleFirstThrowableSelected() {
		toggleThrowableSelected(0);
	}

	private void toggleSecondThrowableSelected() {
		toggleThrowableSelected(1);
	}

	private void toggleThirdThrowableSelected() {
		toggleThrowableSelected(2);
	}

	private void toggleFourthThrowableSelected() {
		toggleThrowableSelected(3);
	}

	private void toggleFifthThrowableSelected() {
		toggleThrowableSelected(4);
	}

	private void currentPlayerThrowThrowables() {
		for (IThrowable t : this.allThrowables) {
			if (!this.throwablesToThrow.contains(t)) {
				this.unthrowableThrowables.add(t);
			}
		}
		ArrayList<IThrowable> tempThrowables = this.findUncalculatedThrowables();
		if (!tempThrowables.isEmpty()) {
			this.tempScoreCalculations.add(this.scoreCalculator.calculateScore(tempThrowables));
		}
		this.throwers.get(this.currentTurn).throwAll(this.throwablesToThrow);
	}
	
	private void showAfterThrowMenu() {
		IMenu menu = new Menu();
		menu.addOption(new Option("Anotar Puntaje", this::storeScore));
		menu.addOption(new Option("Tirar nuevamente", this::selectThrowablesToThrow));

		//TODO: implementar el menu drawer en el codigo

	}

	private ArrayList<IThrowable> findUncalculatedThrowables() {
		ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
		for (IThrowable t : this.unthrowableThrowables) {
			boolean throwableFound = false;
			for (ScoreCalculation s : this.tempScoreCalculations) {
				if (s.getThrowables().contains(t)) {
					throwableFound = true;
					break;
				}
			}
			if (!throwableFound) {
				tempThrowables.add(t);
			}
		}
		return tempThrowables;
	}

}
