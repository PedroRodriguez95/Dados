package View;
import java.util.ArrayList;

import Interfaces.IThrowable;
import Model.Player;

public class HigherThrowDrawer {
	
	public void drawThrower(Player player) {
		System.out.println("*************************");
		System.out.println("Tirador: " + player.getName() );
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
	
	public void drawTieThrow(Player player, int score) {
		System.out.println("*************************");
		System.out.println("El Jugador " + player.getName() + " empata");
		System.out.println("el tiro anterior de " + score +" y ");
		System.out.println("tira de nuevo");
		System.out.println("*************************");
	}
	
	public void drawActualRecord(int score) {
		System.out.println("Record actual: " + score);
	}
	
	public void drawWinner(Player player, int score) {
		System.out.println("*************************");
		System.out.println("Ganador: " + player.getName());
		System.out.println("Puntaje: " + score);
		System.out.println("*************************");
		
	}
	
	public void drawNewRecord(int score) {
		System.out.println("Nuevo Record: " + score);
	}

}
