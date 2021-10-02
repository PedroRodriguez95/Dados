package Model;

import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerListener;

public class Thrower implements IThrower {

    private IThrowerListener listener;


    @Override
	public void throwAll(ArrayList<IThrowable> throwables) {
		this.listener.onThrowStart(this);
		for (IThrowable t : throwables) {
			t.beThrown();
		}
		this.listener.onThrowStop(this);

	}

    @Override
	public void subscribe(IThrowerListener listener) {
		this.listener = listener;
	}
  
}
