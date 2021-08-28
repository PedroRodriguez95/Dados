package Model;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;

public class Player implements IThrower {
	
	private String name;
	private IThrowerListener listener;
	
	public Player(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	

	@Override
	public void throwAll(ArrayList<IThrowable> throwables) {
		this.listener.onThrowStart(this);
		for (IThrowable t : throwables) {
			t.beThrown();
			this.listener.onThrow(this, t);
		}
		this.listener.onThrowStop(this);

	}

	@Override
	public void subscribe(IThrowerListener listener) {
		this.listener = listener;
	}
}
