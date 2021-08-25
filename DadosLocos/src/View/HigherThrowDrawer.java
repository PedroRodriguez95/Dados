package View;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Interfaces.IThrower;

public class HigherThrowDrawer {
	
	public void drawThrower(IThrower thrower) {
		System.out.println("*************************");
		System.out.println("Tirador: " + thrower.getName() );
	}
	
	public void drawThrowables(ArrayList<IThrowable> throwables) {
		for (int i = 1; i <= throwables.size(); i++) {
			System.out.println("dado " + i + ": " + throwables.get(i-1).getValue());
		}
	}
	
	public void drawScore(int score) {
		System.out.println("*************************");
		System.out.println("Puntaje: " + score);
		
	}
	
	public void drawTieThrow(IThrower thrower, int score) {
		System.out.println("*************************");
		System.out.println("El Jugador " + thrower.getName() + " empata");
		System.out.println("el tiro anterior de " + score +" y ");
		System.out.println("tira de nuevo");
		System.out.println("*************************");
	}
	
	public void drawActualRecord(int score) {
		System.out.println("Record actual: " + score);
	}
	
	public void drawWInner(IThrower thrower, int score) {
		System.out.println("*************************");
		System.out.println("Ganador: " + thrower.getName());
		System.out.println("Puntaje: " + score);
		System.out.println("*************************");
		
	}
	
	public void drawNewRecord(int score) {
		System.out.println("Nuevo Record: " + score);
	}

}
