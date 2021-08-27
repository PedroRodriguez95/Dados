package Model;
import java.util.ArrayList;

import Interfaces.IGame;
import Interfaces.IThrowable;
import Interfaces.IThrower;
import View.HigherThrowDrawer;

public class GameHigherThrow implements IGame {
	
	private ArrayList<IThrowable> throwables = new ArrayList<IThrowable>();
	private ArrayList<IThrower> throwers = new ArrayList<IThrower>();
	private HigherThrowDrawer drawer = new HigherThrowDrawer();
	private int score = 0;
	private IThrower winner;
	
	
	@Override
	public ArrayList<IThrowable> getThrowables(){
		return this.throwables;
	}
	
	@Override
	public ArrayList<IThrower> getThrowers(){
		return this.throwers;
	}

	@Override
	public void setUp(int throwableAmount, int throwableFaces,int players) {
		this.throwables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.throwers = new PlayerFactory(players).generatePlayers(this);
	}

	@Override
	public void update(IThrower thrower) {
		
		int newScore = this.getThrowableSum();
		
		this.drawer.drawThrower(thrower);
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

	@Override
	public void start() {
		
		for (IThrower t : this.getThrowers()) {
			t.throwAll(this.getThrowables());
		}
		this.drawer.drawWInner(this.winner, this.score);
		
	}
	
	private int getThrowableSum() {
		int sum = 0;
		for(IThrowable t : this.throwables) {
			sum += t.getValue();
		}
		return sum;
	}

	
}
