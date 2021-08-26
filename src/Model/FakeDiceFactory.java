package Model;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowableFactory;

public class FakeDiceFactory implements IThrowableFactory {
	
	private int[] values;
	
	public FakeDiceFactory(int...values) {
		this.values = values;
		this.generateThrowables();
	}

	@Override
	public ArrayList<IThrowable> generateThrowables(){
		
	ArrayList<IThrowable> dices = new ArrayList<IThrowable>();
		
		for(int value : this.values) {
			Dice d = new Dice(6);
			d.setRandomizer(new FakeRandomizer(value));
			dices.add(d);
		}
		
		return dices;
	}
}
