import Interfaces.IGame;
import Model.GameHigherThrow;

public class Main {

	public static void main(String[] args) {
		
		IGame juego = new GameHigherThrow(3,6,3);
		juego.setUp();
	}

}
