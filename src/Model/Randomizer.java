package Model;
import java.util.Random;

import Interfaces.IRandomizer;

public class Randomizer implements IRandomizer {
	
	//Clase que encapsula el objeto random
	
	Random random = new Random();

	@Override
	public int getRandom(int limit) {
		return this.random.nextInt(limit) + 1;
	}
	
}
