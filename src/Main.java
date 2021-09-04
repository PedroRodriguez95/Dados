import Interfaces.IGame;
import Model.GameHigherThrow;

public class Main {

	public static void main(String[] args) {
		
		IGame game = new GameHigherThrow(3,6,3);
		game.setUp();
	}

}
