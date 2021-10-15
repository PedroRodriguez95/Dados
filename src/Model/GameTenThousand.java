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
	private ArrayList<Player> throwers = new ArrayList<Player>();
	private HashMap<IThrower,Integer> scores = new HashMap<IThrower,Integer>();
	private IThrowScoreCalculator scoreCalculator = new ScoreCalculator(new IThrowScoreCalculator[] { new VerifierAllDices(), new VerifierFourDices(), new VerifierThreeDices(), new VerifierTwoOrLessDices() });
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
		// TODO: Separar el calculo de puntaje por tirada.
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

		this.resetThrowables();
		this.currentPlayerThrowThrowables();
	}

	private void writeScore() {
		int score = this.scoreCalculator.calculateScore(this.allThrowables).getScore();
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
		// TODO: Solo permitir separar dados que fueron tirados en la tirada actual 
		for (int i = 0; i < this.allThrowables.size(); i++) {
			IThrowable throwable = this.allThrowables.get(i);
			String throwableLabel = "" + (i + 1) + " (" + throwable.getValue() + ")";
			if (this.throwablesToThrow.contains(throwable)) {
				menu.addOption(new Option("Separar dado " + throwableLabel, actions.get(i)));
			} else {
				// TODO: Buscar sinonimo de reintegrar
				menu.addOption(new Option("Reintegrar dado " + throwableLabel, actions.get(i)));
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
		this.throwers.get(this.currentTurn).throwAll(this.throwablesToThrow);
	}
	
	private void showAfterThrowMenu() {
		IMenu menu = new Menu();
		menu.addOption(new Option("Anotar Puntaje", this::writeScore));
		menu.addOption(new Option("Tirar nuevamente", this::selectThrowablesToThrow));

	}



}
