package Model;
import Interfaces.IRandomizer;

public class FakeRandomizer implements IRandomizer {

	//TODO: recibir una lista de valores en lugar de uno y devolverlos secuencialmente
	
	private int testValue;
	
	public FakeRandomizer(int testValue ) {
		this.testValue = testValue;
	}

	@Override
	public int getRandom(int amount) {
		return this.testValue;
	}

}
