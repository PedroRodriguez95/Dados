package Model;
import java.util.ArrayList;

import Interfaces.IRandomizer;
import Interfaces.IThrowable;
import Interfaces.IThrowableFactory;

public class DiceFactory implements IThrowableFactory {
	
	int faces = 0;
	IRandomizer random;
	
	public DiceFactory(int faces, IRandomizer random) {
		this.faces = faces;	
		this.random = random;
	}

	@Override
	public IThrowable generateThrowable(){
		Dice dice = new Dice(this.faces);
		dice.setRandomizer(this.random);
		return dice;
	}

	public ArrayList<IThrowable> generateThrowables(int amount){
		
		ArrayList<IThrowable> dices = new ArrayList<IThrowable>();
		
		for(int i = 0; i < amount; i++) {
			dices.add(this.generateThrowable());
		}
		
		return dices;
	}

}
