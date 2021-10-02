package Model;
import java.util.ArrayList;

import Interfaces.IGame;
import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;
import View.HigherThrowDrawer;

public class GameHigherThrow implements IGame, IThrowerListener {
	
	private ArrayList<IThrowable> throwables = new ArrayList<IThrowable>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private HigherThrowDrawer drawer = new HigherThrowDrawer();
	private PlayerNull nullPlayer = new PlayerNull();
	private int score = 0;
	private IThrower winner;
	
	public GameHigherThrow(int throwableAmount, int throwableFaces, int players){
		this.throwables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.players = new PlayerFactory().generatePlayers(players, new ThrowerFactory(), this);
	}

	@Override
	public void setUp() {
		for (IThrower t : this.players) {
			t.throwAll(this.throwables);
		}
		this.drawer.drawWinner(this.getPlayer(this.winner), this.score);
	}
	
	private int getThrowableSum() {
		int sum = 0;
		for(IThrowable t : this.throwables) {
			sum += t.getValue();
		}
		return sum;
	}

	@Override
	public void onThrowStart(IThrower thrower) {
		this.drawer.drawThrower(this.getPlayer(thrower));
	}

	@Override
	public void onThrowStop(IThrower thrower) {

		int newScore = this.getThrowableSum();
		this.drawer.drawThrowables(this.throwables);
		this.drawer.drawScore(newScore);
		this.drawer.drawActualRecord(this.score);

		if (this.score == newScore) {
			this.drawer.drawTieThrow(this.getPlayer(thrower), newScore);
			thrower.throwAll(this.throwables);
		}
		if (this.score < newScore) {
			this.drawer.drawNewRecord(newScore);
			this.score = newScore;
			this.winner = thrower;
		}
		
	}

	private Player getPlayer(IThrower thrower) {
		for (Player p : this.players) {
			if (p == thrower) {
				return p;
			}
		}
		return this.nullPlayer;
	}

	
}
