import Interfaces.IGame;
import Model.GameTenThousand;

public class Main {

	public static void main(String[] args) {
		
		IGame game = new GameTenThousand(5,6,2);
		game.setUp();
	}

}
