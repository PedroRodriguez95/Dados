package Model;

import Interfaces.IThrowerFactory;
import Interfaces.IThrower;

public class ThrowerFactory implements IThrowerFactory {


	@Override
	public IThrower generateThrower() {
		return new Thrower();
	}

}
