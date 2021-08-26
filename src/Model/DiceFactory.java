package Model;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrowableFactory;

public class DiceFactory implements IThrowableFactory {
	int faces = 0;
	int diceAmount=0;
	
	public DiceFactory(int diceAmount, int faces) {
		this.diceAmount = diceAmount;
		this.faces = faces;	
	}

	@Override
	public ArrayList<IThrowable> generateThrowables(){
		
		ArrayList<IThrowable> dices = new ArrayList<IThrowable>();
		
		for(int i = 0; i < this.diceAmount; i++) {
			Dice d = new Dice(this.faces);
			dices.add(d);
		}
		
		return dices;
	}

}
