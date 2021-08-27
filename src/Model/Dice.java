package Model;
import Interfaces.IRandomizer;
import Interfaces.IThrowable;

public class Dice implements IThrowable {
	
	
	private IRandomizer randomizer = new Randomizer();	
	private int faces;
	
	private int upFace = 0;
	
	public Dice(int faces) {
		this.faces = faces;
	}
		
	public void setRandomizer(IRandomizer r) {
		this.randomizer = r;
	}

	@Override
	public void beThrown() {

		this.upFace = this.randomizer.getRandom(this.faces);
	}
	
	public boolean wasThrown() {
		return this.upFace > 0;
	}

	@Override
	public int getValue() {
		return this.upFace;
		
	}
}
