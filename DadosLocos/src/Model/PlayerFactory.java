package Model;
import java.util.ArrayList;

import Interfaces.IGame;
import Interfaces.IThrower;

public class PlayerFactory {
	
	private int amount;
	
	public PlayerFactory(int amount){
		this.amount = amount;
	}
	
	public ArrayList<IThrower> generatePlayers(IGame game){
		
		ArrayList<IThrower> players = new ArrayList<IThrower>();
		
		for (int i= 0; i < this.amount; i++) {
			Player newPlayer = new Player("Jugador " + (i+1));
			newPlayer.subscribe(game);
			players.add(newPlayer);
		}
		return players;
	}

}
