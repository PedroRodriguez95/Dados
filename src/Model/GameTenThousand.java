package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Controller.ValueReaderConsole;
import Interfaces.IAction;
import Interfaces.IGame;
import Interfaces.IMenu;
import Interfaces.IMenuDrawer;
import Interfaces.IPrinter;
import Interfaces.IThrowScoreCalculator;
import Interfaces.IThrowable;
import Interfaces.IThrowableDrawer;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;
import Interfaces.IValueReader;
import View.MenuDrawer;
import View.PrinterConsole;
import View.ThrowableDrawer;

public class GameTenThousand implements IGame, IThrowerListener {

	private ArrayList<IThrowable> allThrowables = new ArrayList<IThrowable>();
	private ArrayList<IThrowable> throwablesToThrow = new ArrayList<IThrowable>();
	private ArrayList<IThrowable> unthrowableThrowables = new ArrayList<IThrowable>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private HashMap<IThrower, Integer> scores = new HashMap<IThrower, Integer>();
	private IThrowScoreCalculator scoreCalculator = new ScoreCalculator(new IThrowScoreCalculator[] {
			new VerifierAllDices(), new VerifierFourDices(), new VerifierThreeDices(), new VerifierTwoOrLessDices() });
	private ArrayList<ScoreCalculation> tempScoreCalculations = new ArrayList<ScoreCalculation>();
	private int currentTurn = 0;
	private IPrinter printer = new PrinterConsole();
	private IMenuDrawer menuDrawer = new MenuDrawer(this.printer);
	private IThrowableDrawer throwableDrawer = new ThrowableDrawer(this.printer);
	private IValueReader valueReader = new ValueReaderConsole(this.printer);
	private ArrayList<IAction> selectThrowableActions = new ArrayList<>();

	public GameTenThousand(int throwableAmount, int throwableFaces, int players) {
		this.allThrowables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.players = new PlayerFactory().generatePlayers(players, new ThrowerFactory(), this);
		this.resetThrowables();
	}

	private void resetThrowables() {
		this.throwablesToThrow.clear();
		for (IThrowable throwable : this.allThrowables) {
			this.throwablesToThrow.add(throwable);
		}
		this.unthrowableThrowables.clear();
	}

	@Override
	public void setUp() {
		for (IThrower t : this.players) {
			this.scores.put(t, 0);
		}
		this.setUpSelectThrowablesActions();
		this.currentPlayerThrowThrowables();
	}

	@Override
	public void onThrowStart(IThrower thrower) {
		// Intencionalmente vacio
	}

	@Override
	public void onThrowStop(IThrower thrower) {
		this.throwableDrawer.drawThrowables(this.allThrowables);
		ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
		tempThrowables.addAll(this.throwablesToThrow);
		ScoreCalculation tempCalculation = this.scoreCalculator.calculateScore(tempThrowables);
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
		if (this.scores.get(this.getCurrentPlayer()) >= 10000) {
			this.printer.print("Ganador: " + this.getCurrentPlayer().getName());
		} else {
			this.currentTurn++;
			if (this.currentTurn >= this.players.size()) {
				this.currentTurn = 0;
			}
			this.tempScoreCalculations.clear();
			this.resetThrowables();
			this.currentPlayerThrowThrowables();
		}
	}

	private Player getCurrentPlayer() {
		return this.players.get(this.currentTurn);
	}

	private void storeScore() {
		int score = this.getTotalScore();
		IThrower thrower = this.getCurrentPlayer();
		this.scores.put(thrower, this.scores.get(thrower) + score);
		this.nextTurn();
	}

	private void setUpSelectThrowablesActions() {
		for (int i = 0; i < this.allThrowables.size(); i++) {
			this.selectThrowableActions.add(new ActionWrapperIntParameter(this::toggleThrowableSelected, i));
		}
	}

	private void selectThrowablesToThrow() {
		IMenu menu = new Menu();

		for (int i = 0; i < this.allThrowables.size(); i++) {
			IThrowable throwable = this.allThrowables.get(i);
			String throwableLabel = "" + (i + 1) + " (" + throwable.getValue() + ")";
			if (this.throwablesToThrow.contains(throwable)) {
				menu.addOption(new Option("Separar dado " + throwableLabel, this.selectThrowableActions.get(i)));
			} else if (!this.unthrowableThrowables.contains(throwable)) {
				menu.addOption(new Option("Retornar dado " + throwableLabel, this.selectThrowableActions.get(i)));
			}
		}
		if (this.getSeparatedThrowables().size() > 0) {
			menu.addOption(new Option("Anotar dados separados", this::calculateScore));
		} else {
			menu.addOption(new Option("Tirar", this::currentPlayerThrowThrowables));
		}

		if (!this.unthrowableThrowables.isEmpty()) {
			menu.addOption(new Option("Cancelar", this::showAfterThrowMenu));
		}
		this.menuDrawer.drawMenu(menu);
		int choice = this.valueReader.readValue(1, menu.getOptions().size());
		menu.execute(choice - 1);
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

	private void currentPlayerThrowThrowables() {
		this.printer.print("Turno: " + this.players.get(this.currentTurn).getName() + " Puntaje: "
				+ this.getPlayerScore(this.currentTurn));
		for (IThrowable t : this.allThrowables) {
			if (!this.throwablesToThrow.contains(t)) {
				this.unthrowableThrowables.add(t);
			}
		}
		ArrayList<IThrowable> tempThrowables = this.findUncalculatedThrowables();
		if (!tempThrowables.isEmpty()) {
			this.tempScoreCalculations.add(this.scoreCalculator.calculateScore(tempThrowables));
		}
		this.players.get(this.currentTurn).throwAll(this.throwablesToThrow);
	}

	private void showAfterThrowMenu() {
		int calculation = this.getTotalScore();
		if (calculation != 0) {
			if (this.unthrowableThrowables.size() == this.allThrowables.size()) {
				this.storeScore();
			} else {
				IMenu menu = new Menu();
				menu.addOption(new Option("Anotar Puntaje (" + calculation + ")", this::storeScore));
				menu.addOption(new Option("Separar Dados", this::selectThrowablesToThrow));
				this.menuDrawer.drawMenu(menu);
				int choice = this.valueReader.readValue(1, menu.getOptions().size());
				menu.execute(choice - 1);
			}
		} else {
			this.selectThrowablesToThrow();
		}

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

	private int getTotalScore() {
		int sum = 0;
		for (ScoreCalculation s : this.tempScoreCalculations) {
			sum += s.getScore();
		}
		return sum;
	}

	private int getPlayerScore(int playerNumber) {
		IThrower thrower = this.players.get(playerNumber);
		return this.scores.get(thrower);
	}

	private ArrayList<IThrowable> getSeparatedThrowables() {
		ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
		for (IThrowable t : this.allThrowables) {
			if (!this.unthrowableThrowables.contains(t) && !this.throwablesToThrow.contains(t)) {
				tempThrowables.add(t);
			}
		}
		return tempThrowables;
	}

	private void calculateScore() {

		ArrayList<IThrowable> tempThrowables = this.getSeparatedThrowables();
		ScoreCalculation tempCalculation = this.scoreCalculator.calculateScore(tempThrowables);
		if (tempCalculation.getScore() != 0) {
			this.tempScoreCalculations.add(tempCalculation);
			for (IThrowable t : tempCalculation.getThrowables()) {
				this.unthrowableThrowables.add(t);
			}
		} else {
			this.printer.print("Jugada no valida");
			for (IThrowable t : tempThrowables) {
				this.throwablesToThrow.add(t);
			}
		}

		if (!this.throwablesToThrow.isEmpty()) {
			this.selectThrowablesToThrow();
		} else {
			this.showAfterThrowMenu();
		}

	}

}
