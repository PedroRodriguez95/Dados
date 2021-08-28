package Model;
import java.util.ArrayList;

import Interfaces.IGame;
import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;
import View.HigherThrowDrawer;

public class GameHigherThrow implements IGame, IThrowerListener {
	
	private ArrayList<IThrowable> throwables = new ArrayList<IThrowable>();
	private ArrayList<IThrower> throwers = new ArrayList<IThrower>();
	private HigherThrowDrawer drawer = new HigherThrowDrawer();
	private int score = 0;
	private IThrower winner;
	
	public GameHigherThrow(int throwableAmount, int throwableFaces,int players){
		this.throwables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.throwers = new PlayerFactory(players).generatePlayers(this);
	}

	@Override
	public void setUp() {
		for (IThrower t : this.throwers) {
			t.throwAll(this.throwables);
		}
		this.drawer.drawWInner(this.winner, this.score);
	}

	@Override
	public void update() {
		//Intentionally empty
	}

	
	private int getThrowableSum() {
		int sum = 0;
		for(IThrowable t : this.throwables) {
			sum += t.getValue();
		}
		return sum;
	}

	@Override
	public void onThrow(IThrower thrower, IThrowable throwable) {
		//Intentionally empty
	}

	@Override
	public void onThrowStart(IThrower thrower) {
		this.drawer.drawThrower(thrower);
	}

	@Override
	public void onThrowStop(IThrower thrower) {

		int newScore = this.getThrowableSum();
		this.drawer.drawThrowables(this.throwables);
		this.drawer.drawScore(newScore);
		this.drawer.drawActualRecord(this.score);

		if (this.score == newScore) {
			this.drawer.drawTieThrow(thrower, newScore);
			thrower.throwAll(this.throwables);
		}
		if (this.score < newScore) {
			this.drawer.drawNewRecord(newScore);
			this.score = newScore;
			this.winner = thrower;
		}
		
	}

	
}
