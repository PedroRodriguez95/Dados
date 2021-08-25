package Model;
import java.util.ArrayList;

import Interfaces.IGame;
import Interfaces.IThrowable;
import Interfaces.IThrower;

public class Player implements IThrower {
	
	private String name;
	private IGame game;
	
	public Player(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	

	@Override
	public void throwAll(ArrayList<IThrowable> throwables) {
		for (IThrowable t : throwables) {
			t.beThrown();
		}
		this.game.update(this);
	}

	@Override
	public void subscribe(IGame game) {
		this.game = game;
	}
}
