package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.IGame;
import Interfaces.IMenu;
import Interfaces.IThrowScoreCalculator;
import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;

public class GameTenThousand implements IGame, IThrowerListener {
	
	private ArrayList<IThrowable> throwables = new ArrayList<IThrowable>();
	private ArrayList<Player> throwers = new ArrayList<Player>();
	//private IThrowerController controller = new TenThousandThrowerController(this);
	private HashMap<IThrower,Integer> scores = new HashMap<IThrower,Integer>();
	private IThrowScoreCalculator scoreCalculator = new ScoreCalculator(new IThrowScoreCalculator[] { new VerifierAllDices(), new VerifierFourDices(), new VerifierThreeDices(), new VerifierTwoOrLessDices() });
	private int currentTurn = 0;

	public GameTenThousand(int throwableAmount, int throwableFaces,int players){
		this.throwables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.throwers = new PlayerFactory().generatePlayers(players, new ThrowerFactory(), this);

	}

	@Override
	public void setUp() {

		for (IThrower t : this.throwers){
			this.scores.put(t, 0);
		}
		this.throwers.get(this.currentTurn).throwAll(this.throwables);
	}

	//TODO: crear una nueva interfaz y extender la clase player para agregar nuevas responsabilidades

	@Override
	public void onThrowStart(IThrower thrower) {
	}

	@Override
	public void onThrowStop(IThrower thrower) {
		ScoreCalculation tempCalculation = this.scoreCalculator.calculateScore(this.throwables);
		if (tempCalculation.getScore() == 0) {
			this.nextTurn();
		} else {
			IMenu menu = new Menu();
			menu.addOption(new Option("Anotar Puntaje", this::writeScore));
		}
	}

	public ArrayList<IThrowable> getThrowables(){
		return this.throwables;
	}

	private void nextTurn() {
		this.currentTurn++;
		if (this.currentTurn >= this.throwers.size()){
			this.currentTurn = 0;
		}
		this.throwers.get(this.currentTurn).throwAll(this.throwables);
	}

	private void writeScore() {
		int score = this.scoreCalculator.calculateScore(this.throwables).getScore();
		IThrower thrower = this.throwers.get(this.currentTurn);
		this.scores.put(thrower, this.scores.get(thrower) + score);
		this.nextTurn();
	}

}
