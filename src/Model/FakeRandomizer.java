package Model;
import Interfaces.IRandomizer;

public class FakeRandomizer implements IRandomizer {

	private int[] testValues;
	private int counter;
	
	public FakeRandomizer(int[] testValues) {
		this.testValues = testValues;
		this.counter = 0;
	}

	@Override
	public int getRandom(int limit) {
		int value = this.testValues[++counter];
		if (counter == this.testValues.length) {
			counter = 0;
		}
		return value;
	}

}
