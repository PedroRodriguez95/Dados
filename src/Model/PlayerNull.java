package Model;

import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowerListener;

public class PlayerNull extends Player {

    public PlayerNull() {
        super("Player not found", null);
    }

	@Override
	public void throwAll(ArrayList<IThrowable> throwables) {
	}

	@Override
	public void subscribe(IThrowerListener listener) {
	}
    
}
