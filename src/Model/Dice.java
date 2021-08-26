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
		
		int face = this.randomizer.getRandom(this.faces);
		
		this.upFace = face;
		
	}
	
	public boolean wasThrown() {
		if (this.upFace == 0) {
			return false;
		}
		return true;
	}

	@Override
	public int getValue() {
		return this.upFace;
		
	}
}
