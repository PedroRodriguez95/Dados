package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.TenThousandThrowerController;
import Interfaces.IGame;
import Interfaces.IThrowScoreCalculator;
import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerController;
import Interfaces.IThrowerListener;

public class GameTenThousand implements IGame, IThrowerListener {
	
	private ArrayList<IThrowable> throwables = new ArrayList<IThrowable>();
	private ArrayList<IThrowable> setAside = new ArrayList<IThrowable>();
	private ArrayList<Player> throwers = new ArrayList<Player>();
	private IThrowerController controller = new TenThousandThrowerController(this);
	private HashMap<IThrower,Integer> scores = new HashMap<IThrower,Integer>();

	//Verifiers
	private IThrowScoreCalculator[] calculators = new IThrowScoreCalculator[] {new VerifierAllDices(), new VerifierFourDices(), new VerifierThreeDices(), new VerifierTwoOrLessDices()};
	
	public GameTenThousand(int throwableAmount, int throwableFaces,int players){
		this.throwables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.throwers = new PlayerFactory().generatePlayers(players, new ThrowerFactory(), this);
	}

	@Override
	public void setUp() {

		for (IThrower t : this.throwers){
			this.scores.put(t, 0);
		}
		this.start();
	}

	//TODO: crear una nueva interfaz y extender la clase player para agregar nuevas responsabilidades

	@Override
	public void update() {
	}

	@Override
	public void onThrow(IThrower thrower, IThrowable throwable) {
	}

	@Override
	public void onThrowStart(IThrower thrower) {
	}

	@Override
	public void onThrowStop(IThrower thrower) {

	}

	//TODO: crear clase que encapsule este comportamiento implementando IThrowScoreCalculator

	public ScoreCalculation getScoreFromThrow(ArrayList<IThrowable> throwables) {
		int tempScore = 0;
		ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
		for ( IThrowScoreCalculator c : this.calculators){

			ScoreCalculation calculation = c.calculateScore(throwables);
			tempScore += calculation.getScore();
			tempThrowables.addAll(calculation.getThrowables());
		}
		return new ScoreCalculation(tempScore, tempThrowables);
	}

	//TODO:Hacer que itere entre los jugadores hasta que el juego termine
	public void start() {
		for(int i = 0; i < this.throwers.size(); i++) {
			this.resetThrowables();
			this.controller.control(this.throwers.get(i));
			this.controller.play();

		}
	}

	public void resetThrowables(){
		for(IThrowable t : this.setAside){
			this.throwables.add(t);
		}
		this.setAside.clear();
	}

	public ArrayList<IThrowable> getThrowables(){
		return this.throwables;
	}

}
