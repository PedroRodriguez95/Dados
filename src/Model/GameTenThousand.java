package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.TenThousandThrowerController;
import Interfaces.IGame;
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
	private VerifierTwoOrLessDices twoOrLessDices = new VerifierTwoOrLessDices();
	private VerifierThreeDices threeDices = new VerifierThreeDices();
	private VerifierFourDices fourDices = new VerifierFourDices();
	private VerifierAllDices fiveDices = new VerifierAllDices();
	
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

	public int verifyThrow(ArrayList<IThrowable> throwables) {
		int tempScore = 0;
		tempScore += this.fiveDices.verify(throwables);
		tempScore += this.fourDices.verify(throwables);
		tempScore += this.threeDices.verify(throwables);
		tempScore += this.twoOrLessDices.verify(throwables);
		return tempScore;
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
