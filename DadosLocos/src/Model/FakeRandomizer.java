package Model;
import Interfaces.IRandomizer;

public class FakeRandomizer implements IRandomizer {
	
	private int testValue;
	
	public FakeRandomizer(int testValue ) {
		this.testValue = testValue;
	}

	@Override
	public int getRandom(int amount) {
		return this.testValue;
	}

}
