package Model;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;

public class Player implements IThrower {
	
	private String name;
	private IThrower wrappedThrower;
	
	public Player(String name, IThrower thrower) {
		this.name = name;
		this.wrappedThrower = thrower;
	}
	
	public String getName() {
		return this.name;
	}
	

	@Override
	public void throwAll(ArrayList<IThrowable> throwables) {
		this.wrappedThrower.throwAll(throwables);
	}

	@Override
	public void subscribe(IThrowerListener listener) {
		this.wrappedThrower.subscribe(listener);
	}
}
